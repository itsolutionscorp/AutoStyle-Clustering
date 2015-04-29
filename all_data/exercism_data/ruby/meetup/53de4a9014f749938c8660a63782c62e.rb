class Meetup
  def initialize(month, year)
    @month = month
    @year = year
  end

  def day(weekday, schedule)
    send(schedule, weekday)
  end

  private

  def teenth(weekday)
    first_between(13, 19, weekday)
  end

  def first(weekday)
    ordinal(1, weekday)
  end

  def second(weekday)
    ordinal(2, weekday)
  end

  def third(weekday)
    ordinal(3, weekday)
  end

  def fourth(weekday)
    ordinal(4, weekday)
  end

  def ordinal(num, weekday)
    first = 7*(num-1)+1
    last = first + 6
    first_between(first, last, weekday)
  end

  def last(weekday)
    first_between(last_day_of_month, last_day_of_month - 7, weekday)
  end

  def last_day_of_month
    (Date.new(@year, @month, 1).next_month - 1).mday
  end

  def first_between(first, last, weekday)
    range = first < last ? (first..last) : first.downto(last)
    day = range.detect do |date|
      Date.new(@year, @month, date).send("#{weekday.to_s}?")
    end
    Date.new(@year, @month, day)
  end
end
