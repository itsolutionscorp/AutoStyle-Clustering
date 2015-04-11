require "date"

class Meetup
  DAYS = [:monday, :tuesday, :wednesday, :thursday, :friday, :saturday, :sunday]
  SCHEDULES = [:first, :second, :third, :fourth, :last, :teenth]

  def initialize(month, year)
    @month = month
    @year = year
  end

  def day(weekday, schedule)
    @weekday = weekday
    send(schedule) if SCHEDULES.include?(schedule)
  end

  private
  4.times do |i| #first through fourth
    define_method(SCHEDULES[i]) { nth_day(i) }
  end

  def nth_day(n)
    Date.new(@year, @month, first_occurrence_of_weekday + 7*n)
  end
  
  def last
    Date.new(@year, @month, last_occurrence_of_weekday)
  end

  def teenth
    date = second
    teen?(date) ? date : third
  end

  def teen?(date)
    d = date.day
    d >= 13 && d < 20
  end

  def first_occurrence_of_weekday
    first_occurrence = 1 + days_from_first
    if first_occurrence < 1
      first_occurrence + 7
    else
      first_occurrence
    end
  end

  def last_occurrence_of_weekday
    last_occurrence = -1 + days_from_last
    if last_occurrence > -1
      last_occurrence - 7
    else
      last_occurrence
    end
  end

  def days_from_first
    DAYS.index(@weekday) - weekday_of_the_first
  end

  def days_from_last
    DAYS.index(@weekday) - weekday_of_the_last
  end

  def weekday_of_the_last
    last_of_month.cwday - 1
  end

  def weekday_of_the_first
    first_of_month.cwday - 1
  end

  def first_of_month
    Date.new(@year, @month, 1)
  end

  def last_of_month
    Date.new(@year, @month, -1)
  end
end
