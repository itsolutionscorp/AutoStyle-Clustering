require 'date'

class Meetup
  def initialize(month, year)
    @first_of_month = Date.new(year, month)
    @days_in_month = Date.new(year, month, -1).day
  end

  WEEKDAYS = %i[sunday monday tuesday wednesday thursday friday saturday]
  POTENTIAL_WEEKS = %i[first second third fourth]
  def day(weekday, schedule)
    date = @first_of_month
    date += 1 until date.wday == WEEKDAYS.index(weekday)
    case schedule
    when *POTENTIAL_WEEKS
      date += 7 * POTENTIAL_WEEKS.index(schedule)
    when :last
      date += 7 until date.day.between?(@days_in_month-6, @days_in_month)
    when :teenth
      date += 7 until date.day.between?(13, 19)
    end
    date
  end
end
