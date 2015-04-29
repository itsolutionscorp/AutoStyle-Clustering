class Meetup
  SUNDAY = 0
  MONDAY = 1
  TUESDAY = 2
  WEDNESDAY = 3
  THURSDAY = 4
  FRIDAY = 5
  SATURDAY = 6

  def initialize(month, year)
    @month = month
    @year = year
  end

  %w{sun mon tues wednes thurs fri satur}.each do |prefix|
    the_day = self.const_get("#{prefix.upcase}DAY")

    define_method "#{prefix}teenth" do
      someteenth the_day
    end

    define_method "first_#{prefix}day" do
      first_someday the_day
    end

    define_method "second_#{prefix}day" do
      second_someday the_day
    end

    define_method "third_#{prefix}day" do
      third_someday the_day
    end

    define_method "fourth_#{prefix}day" do
      fourth_someday the_day
    end

    define_method "last_#{prefix}day" do
      last_someday the_day
    end
  end

private

  def someteenth(day_of_week)
    someday(day_of_week, 13..19)
  end

  def first_someday(day_of_week)
    someday(day_of_week, 1..7)
  end

  def second_someday(day_of_week)
    someday(day_of_week, 8..14)
  end

  def third_someday(day_of_week)
    someday(day_of_week, 15..21)
  end

  def fourth_someday(day_of_week)
    someday(day_of_week, 22..28)
  end

  def last_someday(day_of_week)
    first_day_of_the_next_month = if @month < 12
      Date.new(@year, @month + 1, 1)
    else
      Date.new(@year + 1, 1, 1)
    end
    last_day_of_this_month = first_day_of_the_next_month.day - 1
    someday(day_of_week, (last_day_of_this_month-7)..last_day_of_this_month)
  end

  def someday(day_of_week, range)
    current_date = nil
    range.each do | mday |
      current_date = Date.new(@year, @month, mday)
      break if current_date.wday == day_of_week
    end
    current_date
  end



end
