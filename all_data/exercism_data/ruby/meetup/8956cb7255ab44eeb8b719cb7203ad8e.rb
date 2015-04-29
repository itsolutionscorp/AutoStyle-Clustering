class Meetup

  WEEKDAYS = %i[sunday monday tuesday wednesday thursday friday saturday]
  WEEKDAY_TYPES = %i[first second third fourth]
  DAYS_IN_WEEK = 7

  def initialize(month, year)
    @month = month
    @year = year
  end

  def day(weekday, day_type)
    desired_weekday = weekday_number(weekday)
    case day_type
    when :teenth
      find_teenth(desired_weekday)
    when :last
      find_last(desired_weekday)
    else
      find_nth(desired_weekday, nth_week(day_type))
    end
  end

  private

  def find_teenth(desired_weekday)
    first_thirteenth = Date.new(@year, @month, 13)
    first_thirteenth + day_gap(first_thirteenth, desired_weekday)
  end

  def find_last(desired_weekday)
    start_of_last_week = Date.new(@year, @month, -1) - (DAYS_IN_WEEK - 1)
    gap = day_gap(start_of_last_week, desired_weekday)
    start_of_last_week + gap
  end

  def find_nth(desired_weekday, week)
    first_of_month = Date.new(@year, @month, 1)
    gap = day_gap(first_of_month, desired_weekday)
    first_day_of_month = first_of_month + gap
    first_day_of_month + week * DAYS_IN_WEEK
  end

  def day_gap(start_day, desired_weekday)
    start_day_weekday = start_day.wday
    gap = desired_weekday - start_day_weekday
    gap += DAYS_IN_WEEK if gap < 0
    gap
  end

  def weekday_number(weekday_symbol)
    WEEKDAYS.index(weekday_symbol)
  end

  def nth_week(day_type)
    WEEKDAY_TYPES.index(day_type)
  end

end
