package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int = when {
        year != other.year -> year.compareTo(other.year)
        month != other.month -> month.compareTo(other.month)
        else -> dayOfMonth.compareTo(other.dayOfMonth)
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate> {
    operator fun iterator(): Iterator<MyDate> {
        var next = start
        return object : Iterator<MyDate> {

            override fun hasNext(): Boolean = next <= endInclusive

            override fun next(): MyDate {
                val ret = next;
                next = next.nextDay();
                return ret;
            }
        }
    }
}
