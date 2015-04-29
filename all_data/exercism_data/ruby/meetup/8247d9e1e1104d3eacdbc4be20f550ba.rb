class Meetup
  attr_reader :year, :month

  def initialize(month, year)
    @month = month
    @year = year
  end

  def day(day_name, type)
    weekday = weekday_from_name(day_name)
    day = case type
          when :first
            first_weekday(weekday)
          when :second
            first_weekday(weekday, from_day: 8)
          when :teenth
            first_weekday(weekday, from_day: 13)
          when :third
            first_weekday(weekday, from_day: 15)
          when :fourth
            first_weekday(weekday, from_day: 22)
          when :last
            last_weekday(weekday)
          end

    Date.new(year, month, day)
  end

  private

  def first_weekday(target_weekday, from_day: 1)
    from_day + (target_weekday - weekday_of(from_day)) % 7
  end

  def last_weekday(target_weekday)
    last_day_of_month - (last_weekday_of_month - target_weekday) % 7
  end

  def last_day_of_month
    Date.new(year, month, -1).day
  end

  def last_weekday_of_month
    weekday_of(last_day_of_month)
  end

  def weekday_of(day)
    Date.new(year, month, day).wday
  end

  def weekday_from_name(day_name)
    Date::DAYNAMES.index(day_name.to_s.capitalize)
  end
end
