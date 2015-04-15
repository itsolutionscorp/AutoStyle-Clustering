require 'date'

class Meetup
  @teenth = (13..19)

  DAYS = {
    sunday: 0,
    monday: 1,
    tuesday: 2,
    wednesday: 3,
    thursday: 4,
    friday: 5,
    saturday: 6
  }

  attr_reader :month, :year, :date

  def initialize(month, year)
    @month = month
    @year = year
  end

  def day(weekday, schedule)
    @date = Date.new(year, month)
    send(schedule, weekday)
  end

  private

  def first(weekday)
    @date += 1 while date.wday != DAYS[weekday]
    date
  end

  def second(weekday)
    first(weekday) + 7
  end

  def third(weekday)
    first(weekday) + 14
  end

  def fourth(weekday)
    first(weekday) + 21
  end

  def last(weekday)
    @date = date.next_month - 1
    @date -= 1 while date.wday != DAYS[weekday]
    date
  end

  def teenth(weekday)
    @date = Date.new(year, month, 13)
    @date += 1 while date.wday != DAYS[weekday]
    date
  end
end
