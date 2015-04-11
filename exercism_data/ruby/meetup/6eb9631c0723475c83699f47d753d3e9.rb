class Meetup

  def initialize (month, year)
    @month = month
    @year = year
    @daynames_with_dates = create_daynames_with_dates
  end

  def day(weekday, schedule)
    return Date.new(@year, @month, @daynames_with_dates[weekday][0]) if schedule == :first
    return Date.new(@year, @month, @daynames_with_dates[weekday][1]) if schedule == :second
    return Date.new(@year, @month, @daynames_with_dates[weekday][2]) if schedule == :third
    return Date.new(@year, @month, @daynames_with_dates[weekday][3]) if schedule == :fourth
    return Date.new(@year, @month, @daynames_with_dates[weekday][-1]) if schedule == :last
    return Date.new(@year, @month, teenth(weekday)) if schedule == :teenth
  end

  def teenth(weekday)
    teenthday = 0
    @daynames_with_dates[weekday].each do |array_value|
      teenthday = array_value if array_value >= 13 && array_value <= 19
    end
    return teenthday
  end

  def create_daynames_with_dates
    weekdays_with_days_of_month = Hash.new([])
    (1..31).each do |day_of_month|
      if Date.valid_date?(@year, @month, day_of_month)
        weekdays_with_days_of_month[dayname(day_of_month)] += [day_of_month]
      end
    end
    return weekdays_with_days_of_month
  end

  def dayname(day_of_month)
    if Date.valid_date?(@year, @month, day_of_month)
      Date.new(@year, @month, day_of_month).strftime('%A').downcase.to_sym
    end
  end
end
