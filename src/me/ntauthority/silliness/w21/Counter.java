package me.ntauthority.silliness.w21;

import com.google.java.contract.*;

/**
 * A simple counter object, because anything more useful would be too complex.
 */
@Invariant("value >= 0") // the value shall not be negative
public class Counter
{
    private int value;
    private int limit;

    /**
     * Resets the counter to its initial state.
     */
    @Ensures({"value == 0", "limit == 0"})
    public void reset()
    {
        // initial state for primitive 'int' types is 0.
        value = 0;
        limit = 0;
    }

    /**
     * Adds a tick to the counter tally, ensuring to not exceed the limit.
     * @return whether the limit was exceeded or not
     */
    @Ensures({"limit == 0 || value <= limit", "value == limit || value == (old(value) + 1)"})
    public boolean count()
    {
        // clamp counter and return failure if.. we can't increment any further
        if (limit != 0 && value >= limit)
        {
            return false;
        }

        // increment and return a success state
        value = value + 1;

        return true;
    }

    /**
     * Returns the current counter value.
     * @return the counter value
     */
    @Ensures("result == value")
    public int getValue()
    {
        return value;
    }

    /**
     * Reduces the counter tally by one tick.
     */
    @Ensures("value < old(value) || old(value) == 0")
    public void undo()
    {
        // if the value would not end up negative...
        if (value > 0)
        {
            // ... subtract one from said counter.
            value = value - 1;
        }
    }

    /**
     * Sets the maximum value of the counter.
     * This method will silently clamp the value to the new limit.
     * @param maximum new maximum value, any positive integer. 0 disables the limit.
     */
    @Requires("maximum >= 0")
    @Ensures("limit == 0 || value <= limit")
    public void setLimit(int maximum)
    {
        // a limit of 0 implies the limit gets disabled
        // a negative limit is outside the scope of this implementation.
        limit = maximum;

        // clamp value to not exceed the limit, if not removing the limit
        if (limit > 0 && value > limit)
        {
            value = limit;
        }
    }
}
