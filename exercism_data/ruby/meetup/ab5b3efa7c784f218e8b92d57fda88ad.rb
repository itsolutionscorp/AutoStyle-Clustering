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
    send(schedule)
  end

  private
  4.times do |i| #first through fourth
    define_method(SCHEDULES[i]) { nth_day(i) }
  end
  
  def last
    Date.new(@year, @month, last_occurence_of_weekday)
  end

  def teenth
    date = second
    if teen? date
      date
    else
      third
    end
  end

  def teen?(date)
    d = date.day
    d >= 13 && d < 20
  end

  def nth_day(n)
    Date.new(@year, @month, first_occurence_of_weekday + 7*n)
  end

  def first_of_month
    @fom ||= Date.new(@year, @month, 1)
  end

  def last_of_month
    @lom ||= Date.new(@year, @month, -1)
  end

  def weekday_of_the_last
    last_of_month.cwday - 1
  end

  def weekday_of_the_first
    first_of_month.cwday - 1
  end

  def first_occurence_of_weekday
    first_occurence = 1 + days_from_first
    if first_occurence < 1
      first_occurence + 7
    else
      first_occurence
    end
  end

  def last_occurence_of_weekday
    last_occurence = -1 + days_from_last
    if last_occurence > -1
      last_occurence - 7
    else
      last_occurence
    end
  end

  def days_from_first
    DAYS.index(@weekday) - weekday_of_the_first
  end

  def days_from_last
    DAYS.index(@weekday) - weekday_of_the_last
  end
end
