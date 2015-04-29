module TimeTricks
  COMMON_YEAR_DAYS_IN_MONTH = [nil, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

  def days_in_month(month, year = Time.now.year)
    return 29 if month == 2 && Date.gregorian_leap?(year)
    COMMON_YEAR_DAYS_IN_MONTH[month]
  end
end

class Meetup
  include TimeTricks

  DAYS = {
    sunday:    0,
    monday:    1,
    tuesday:   2,
    wednesday: 3,
    thursday:  4,
    friday:    5,
    saturday:  6,
  }

  def initialize(month, year)
    @base_date = Date.new(year, month, 1)
    @month = month
    @year = year
  end

  def day(weekday, schedule)
    until right_schedule?(schedule) && right_day?(weekday)
      @base_date += 1 
    end
    @base_date
  end

  def what_day
    DAYS.invert[@base_date.wday]
  end

  def right_day?(weekday)
    what_day == weekday
  end

  def right_schedule?(schedule)
    case schedule
    when :teenth
      (13..19) === @base_date.mday
    when :first
      date_range(0) === @base_date.mday
    when :second
      date_range(1) === @base_date.mday
    when :third
      date_range(2) === @base_date.mday
    when :fourth
      date_range(3) === @base_date.mday
    when :last
      (days_in_month(@month,@year)-6..days_in_month(@month,@year)) === @base_date.mday
    else
      raise ArgumentError, "Invalid schedule: #{p schedule}"
    end
  end

  def date_range(week)
    days_in_week = 7
    week_start = ((week * days_in_week)+1) 
    week_finish = week_start + days_in_week - 1
    (week_start..week_finish)
  end

end
