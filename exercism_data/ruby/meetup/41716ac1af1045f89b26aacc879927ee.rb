class Meetup
  attr_reader :month, :year

  WEEKDAY = { first: 0, second: 1, third: 2, fourth: 3, last: -1 }

  def initialize month, year
    @month = month
    @year = year
  end

  def day weekday, schedule
    day = teenth_day(weekday) if schedule == :teenth
    day ||= all_weekdays(weekday)[WEEKDAY[schedule]]
    Date.new(year, month, day)
  end

  private

  def all_weekdays weekday
    (1..days_in_month).select { |day| Date.new(year, month, day).send weekday.to_s + "?" }
  end

  def days_in_month
    (Date.new(year, 12, 31) << (12 - month)).day
  end

  def teenth_day weekday
    all_weekdays(weekday).select { |day| day.between?(13, 19) }.first
  end
end
