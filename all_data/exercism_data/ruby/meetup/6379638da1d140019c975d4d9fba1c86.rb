require 'date'

class Meetup
  DAY_OF_WEEK = {
    sunday:    0,
    monday:    1,
    tuesday:   2,
    wednesday: 3,
    thursday:  4,
    friday:    5,
    saturday:  6
  }

  def initialize(month, year)
    @month = month
    @year  = year
  end

  def day(weekday, schedule)
    date = first_weekday_of_month(weekday)

    case schedule
    when :first then date
    when :second then date += 7
    when :third  then date += 14
    when :fourth then date += 21
    when :last
      date += 28
      date -= 7 unless date.month == month
      date
    when :teenth
      date += 7
      date += 7 unless date.day.between?(13, 19)
      date
    end
  end

  private

  attr_reader :month, :year

  def first_weekday_of_month(weekday)
    date = Date.strptime("#{month} #{year}", '%m %Y')
    date += 7 if date.wday > DAY_OF_WEEK.fetch(weekday)
    date + DAY_OF_WEEK.fetch(weekday) - date.wday
  end
end
