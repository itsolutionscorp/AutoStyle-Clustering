class Meetup
  WEEK_DAY_PREFIXES = %w[sun mon tues wednes thurs fri satur]

  def initialize(month, year)
    @first_day = Date.new(year, month, 1)
    @first_day_next_month = Date.new(year, month, -1) + 1
  end

  WEEK_DAY_PREFIXES.each do |week_day|
    wday = WEEK_DAY_PREFIXES.index(week_day)

    define_method(:"#{week_day}teenth") do
      teenth(wday)
    end

    define_method(:"first_#{week_day}day") do
      first(wday)
    end

    define_method(:"second_#{week_day}day") do
      second(wday)
    end

    define_method(:"third_#{week_day}day") do
      third(wday)
    end

    define_method(:"fourth_#{week_day}day") do
      fourth(wday)
    end

    define_method(:"last_#{week_day}day") do
      last(wday)
    end
  end

  private

  def second(wday)
    first(wday) + one_week
  end

  def third(wday)
    first(wday) + one_week * 2
  end

  def fourth(wday)
    first(wday) + one_week * 3
  end

  def first(wday)
    find_day_in_a_week(first_day, wday)
  end

  def teenth(wday)
    find_day_in_a_week(thirteenth_day, wday)
  end

  def last(wday)
    find_day_in_a_week(first_day_next_month - one_week, wday)
  end

  def find_day_in_a_week(beginning_of_week, wday)
    if wday < beginning_of_week.wday
      wday += one_week
    end
    beginning_of_week + wday - beginning_of_week.wday
  end

  def one_week
    7
  end

  def thirteenth_day
    first_day + 12
  end

  attr_reader :first_day, :first_day_next_month
end
