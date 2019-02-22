package tkt.lajittelualgoritmien_vertailu;

/**
 * Natural merge sort, kehitteillä, hitaalla käy
 */
public class TimsortOf {

    final static int INSERTIONSORT_THRESHOLD = 64;
    final static int RUN_MAX_LENGTH = 63;
    final static int MINRUN = 63;

    public static void sort(int[] arr) {
        if (arr.length < INSERTIONSORT_THRESHOLD) {
            Insertionsort.sort(arr);
        } else {
            timSort(arr);
        }
    }

    private static int[] merge(int[] array, Run run) {
        int[] returnArray = new int[array.length + run.size()];
        int arrayIndex = 0;

        for (int i = 0; i < returnArray.length; i++) {
            // Jos array on tyhjä, lisätään runista
            // Jos run on tyhjä, lisätään arraysta
            if (arrayIndex == array.length) {
                returnArray[i] = run.peekFirst();
                run.increment();
            } else if (run.isEmpty()) {
                returnArray[i] = array[arrayIndex];
                arrayIndex++;
            } else if (array[arrayIndex] <= run.peekFirst()) {
                returnArray[i] = array[arrayIndex];
                arrayIndex++;
            } else {
                returnArray[i] = run.peekFirst();
                run.increment();
            }
        }

        return returnArray;
    }

    public static Run merge(Run run1, Run run2) {
        int size = run1.size() + run2.size();
        Run returnRun = new Run(size, 0);
        returnRun.done();
        for (int i = 0; i < size; i++) {
            if (run1.isEmpty()) {
                returnRun.array[i] = run2.peekFirst();
                run2.increment();
            } else if (run2.isEmpty()) {
                returnRun.array[i] = run1.peekFirst();
                run1.increment();
            } else if (run1.peekFirst() <= run2.peekFirst()) {
                returnRun.array[i] = run1.peekFirst();
                run1.increment();
            } else {
                returnRun.array[i] = run2.peekFirst();
                run2.increment();
            }
        }
        returnRun.resize(size);
        return returnRun;
    }

    private static void timSort(int[] arr) {
        Run run = new Run(RUN_MAX_LENGTH, MINRUN);
        Stack runs = new Stack();
        int[] tempArray = new int[0];

        for (int i = 0; i < arr.length; i++) {
            if (run.canAdd(arr[i])) {
                run.add(arr[i]);
            } else {
                run.done();
                runs.push(run);
                run = new Run(RUN_MAX_LENGTH, MINRUN);
                run.add(arr[i]);
            }
            if (i == arr.length - 1) {
                run.done();
                runs.push(run);
            }
            runs.check();
        }
        
        while (!runs.isEmpty()) {
            tempArray = merge(tempArray, runs.pop());
        }

        System.arraycopy(tempArray, 0, arr, 0, tempArray.length);

    }

}

class Run {

    int[] array;
    boolean desc;
    boolean empty;
    boolean needsSort;
    int index;
    int end;
    int increment;
    int size;
    int minrun;

    Run(int MAX_LENGTH, int MINRUN) {
        array = new int[MAX_LENGTH];
        desc = false;
        empty = true;
        index = 0;
        increment = 1;
        minrun = MINRUN;
    }

    void sort() {
        Insertionsort.sort(array, 0, index - 1);
        desc = false;
        increment = 1;
    }

    boolean canAdd(int NUMBER) {

        if (needsSort) {
            return (index < minrun);
        }
        // Jos run on tyhjä tai siinä on vain yksi luku, voi lisätä
        if (index == 0 || index == 1) {
            return true;
        } // Jos run ei ole täynnä
        else if (index != array.length) {
            // Jos run on laskeva, onko numero aidosti pienempi
            boolean tempNeedsSort;
            if (desc) {
                if (previous() > NUMBER) {
                    return true;
                } else {
                    needsSort = true;
                    return true;
                }
            } // Jos run on nouseva, onko numero yhtä suuri tai suurempi
            else {
                if (previous() <= NUMBER) {
                    return true;
                } else {
                    needsSort = true;
                    return true;
                }
            }
        }
        // Jos run on täynnä niin palautetaan false
        return false;
    }

    void add(int NUMBER) {
        // Jos lisättävä numero on runin toinen numero
        // ja jos ensimmäinen alkio on aidosti suurempi kuin lisättävä
        // niin run on laskeva
        if (index == 1 && (previous() > NUMBER)) {
            descending();
        }

        empty = false;
        array[index] = NUMBER;
        index++;
        size++;
    }

    void descending() {
        desc = true;
        increment = -1;
    }

    void done() {
        if (needsSort) {
            sort();
        }

        if (desc) {
            index--;
            end = 0;
        } else {
            end = index - 1;
            index = 0;
        }
    }

    void resize(int newSize) {
        end = size;
        size = newSize;
        empty = false;

    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        return empty;
    }

    void increment() {
        size--;
        index += increment;
        if (size == 0) {
            empty = true;
        }
    }

    int previous() {
        return array[index - 1];
    }

    int peekFirst() {
        return array[index];
    }
}

class Stack {

    Run[] array;
    int index;

    Stack() {
        array = new Run[10000000];
        index = 0;
    }

    Run peek() {
        return array[index-1];
    }

    boolean isEmpty() {
        return (index == 0);
    }

    void push(Run run) {
        array[index] = run;
        index++;
    }

    Run pop() {
        index--;
        return array[index];
    }

    int size() {
        return index;
    }

    void check() {
        while (index > 2) {
            if (array[index - 3].size() > array[index - 1].size() + array[index - 2].size()) {
                if (array[index - 1].size() > array[index - 2].size()) {
                    push(TimsortOf.merge(pop(), pop()));
                } else {
                    break;
                }
            } else {
                Run temp = pop();
                if (array[index - 2].size() > temp.size()) {
                    push(TimsortOf.merge(pop(), temp));
                } else {
                    push(TimsortOf.merge(pop(), pop()));
                    push(temp);
                }
            }

        }
    }

}
