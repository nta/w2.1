package me.ntauthority.silliness.w21;

public class Main
{

    public static void main(String[] args)
    {
        // test case 1: basic usage
        {
            Counter counter = new Counter();
            counter.count();
            counter.count();

            System.out.format("value: %d (should be 2)\n", counter.getValue());

            counter.count();

            System.out.format("value: %d (should be 3)\n", counter.getValue());

            counter.reset();
            counter.count();

            System.out.format("value: %d (should be 1)\n", counter.getValue());
        }

        // test case 2: undoing
        {
            Counter counter = new Counter();
            counter.count();
            counter.undo();
            counter.undo();
            counter.count();

            System.out.format("value: %d (should be 1)\n", counter.getValue());
        }

        // test case 3: limiting
        {
            Counter counter = new Counter();
            counter.count();
            counter.count();
            counter.setLimit(1);

            System.out.format("value: %d (should be 1)\n", counter.getValue());

            counter.setLimit(3);

            for (int i = 0; i < 5; i++)
            {
                boolean result = counter.count();

                System.out.format("iteration %d %s (value %d)\n", i, (result) ? "succeeded" : "exceeded limit", counter.getValue());
            }
        }

        // point test case
        {
            Point2D p1 = new Point2D(2.0, 2.0);
            Point2D p2 = new Point2D(-2.0, -2.0);

            System.out.format("distance of p1 to origin: %f\n", p1.distanceToOrigin());
            System.out.format("distance of p2 to origin: %f\n", p2.distanceToOrigin());

            System.out.format("distance of p2 to p1: %f\n", p2.distanceToPoint(p1));
            System.out.format("distance of p1 to p2: %f\n", p1.distanceToPoint(p2));

            p2.setY(2.0);

            System.out.format("distance of p2 (w/ y %f) to origin: %f\n", p2.getY(), p2.distanceToOrigin());

            System.out.format("distance of p2 to p1: %f\n", p2.distanceToPoint(p1));
            System.out.format("distance of p1 to p2: %f\n", p1.distanceToPoint(p2));
        }

        // array test case 1
        {
            int[] array = new int[5];
            array[0] = 4;
            array[1] = 5;
            array[2] = 6;
            array[3] = 1;
            array[4] = 2;

            printArray("order move", orderStableMoveEven(array));

            System.out.format("second largest: %d\n", returnSecondLargest(array));

            int[] array2 = new int[5];
            array2[0] = 4;
            array2[1] = 5;
            array2[2] = 6;
            array2[3] = 5;
            array2[4] = 2;

            System.out.format("equivalence: %s\n", setEquivalent(array, array2) ? "yes" : "no");

            array[4] = 5;
            array[3] = 2;

            System.out.format("equivalence: %s\n", setEquivalent(array, array2) ? "yes" : "no");
        }
    }

    /**
     * Outputs an integer array to System.out.
     * @param description a description for the array
     * @param array the array to output
     */
    private static void printArray(String description, int[] array)
    {
        System.out.format("%s (%d items): ", description, array.length);

        for (int val : array)
        {
            System.out.format("%d ", val);
        }

        System.out.print("\n");
    }

    /**
     * Moves even elements to the start of an integer array, order-stable. Complexity is O(n*2).
     * @param array the input array
     * @return the sorted array
     */
    private static int[] orderStableMoveEven(int[] array)
    {
        int[] outArray = new int[array.length];
        int idx = 0;

        for (int even = 0; even <= 1; even++)
        {
            for (int i = 0; i < array.length; i++)
            {
                if ((array[i] % 2) == even)
                {
                    outArray[idx++] = array[i];
                }
            }
        }

        return outArray;
    }

    /**
     * Returns the second-largest integer in an integer array.
     * @param array input array
     * @return second-largest integer
     */
    private static int returnSecondLargest(int[] array)
    {
        int max = 0;
        int max2 = 0;

        for (int val : array)
        {
            if (val > max)
            {
                max2 = max;
                max = val;
            }
        }

        return max2;
    }

    /**
     * Compares two sets of integers for set-based equivalence, passed as integer array.
     * @param left first set
     * @param right second set
     * @return whether the sets are equivalent
     */
    private static boolean setEquivalent(int[] left, int[] right)
    {
        return setEquivalentInternal(left, right) && setEquivalentInternal(right, left);
    }

    /**
     * Helper method for setEquivalent, checking whether all items from the first set exist in the second.
     * @param left first set
     * @param right second set
     * @return one-way equivalence between sets
     */
    private static boolean setEquivalentInternal(int[] left, int[] right)
    {
        for (int i = 0; i < left.length; i++)
        {
            if (!isInSet(right, left[i]))
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks whether an integer array contains a specified integer.
     * @param set integer array set
     * @param value the value to check
     * @return whether or not the value is in the set
     */
    private static boolean isInSet(int[] set, int value)
    {
        for (int i = 0; i < set.length; i++)
        {
            if (set[i] == value)
            {
                return true;
            }
        }

        return false;
    }
}
