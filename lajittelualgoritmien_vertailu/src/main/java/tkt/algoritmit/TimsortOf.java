package tkt.algoritmit;

/**
 * Hidas Timsort ilman laukkaominaisuutta mergessä
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
        this.array = new int[MAX_LENGTH];
        this.desc = false;
        this.empty = true;
        this.index = 0;
        this.increment = 1;
        this.minrun = MINRUN;
    }

    void sort() {
        Insertionsort.sort(this.array, 0, this.index - 1);
        this.desc = false;
        this.increment = 1;
    }

    boolean canAdd(int NUMBER) {

        if (this.needsSort) {
            return (this.index < this.minrun);
        }
        // Jos run on tyhjä tai siinä on vain yksi luku, voi lisätä
        if (this.index == 0 || this.index == 1) {
            return true;
        } // Jos run ei ole täynnä
        else if (this.index != this.array.length) {
            // Jos run on laskeva, onko numero aidosti pienempi
            boolean tempNeedsSort;
            if (this.desc) {
                if (this.previous() > NUMBER) {
                    return true;
                } else {
                    this.needsSort = true;
                    return true;
                }
            } // Jos run on nouseva, onko numero yhtä suuri tai suurempi
            else {
                if (this.previous() <= NUMBER) {
                    return true;
                } else {
                    this.needsSort = true;
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
        if (this.index == 1 && (this.previous() > NUMBER)) {
            this.descending();
        }

        this.empty = false;
        this.array[this.index] = NUMBER;
        this.index++;
        this.size++;
    }

    void descending() {
        this.desc = true;
        this.increment = -1;
    }

    void done() {
        if (this.needsSort) {
            this.sort();
        }
        if (this.desc) {
            this.index--;
            this.end = 0;
        } else {
            this.end = this.index - 1;
            this.index = 0;
        }
    }

    void resize(int newSize) {
        this.end = this.size;
        this.size = newSize;
        this.empty = false;
    }

    int size() {
        return this.size;
    }

    boolean isEmpty() {
        return this.empty;
    }

    void increment() {
        this.size--;
        this.index += this.increment;
        if (this.size == 0) {
            this.empty = true;
        }
    }

    int previous() {
        return this.array[this.index - 1];
    }

    int peekFirst() {
        return this.array[this.index];
    }
}

class Stack {

    Run[] array;
    int index;

    Stack() {
        this.array = new Run[10000000];
        this.index = 0;
    }

    Run peek() {
        return this.array[this.index - 1];
    }

    boolean isEmpty() {
        return (this.index == 0);
    }

    void push(Run run) {
        this.array[this.index] = run;
        this.index++;
    }

    Run pop() {
        this.index--;
        return this.array[this.index];
    }

    int size() {
        return this.index;
    }

    void check() {
        while (this.index > 2) {
            if (this.array[this.index - 3].size() > this.array[this.index - 1].size() + this.array[this.index - 2].size()) {
                if (this.array[this.index - 1].size() > this.array[this.index - 2].size()) {
                    push(TimsortOf.merge(pop(), pop()));
                } else {
                    break;
                }
            } else {
                Run temp = pop();
                if (this.array[this.index - 2].size() > temp.size()) {
                    push(TimsortOf.merge(pop(), temp));
                } else {
                    push(TimsortOf.merge(pop(), pop()));
                    push(temp);
                }
            }
        }
    }
}
