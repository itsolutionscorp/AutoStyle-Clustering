require 'date'
require 'time'
class Meetup
  SCHEDULE = [:first, :second, :third, :fourth]
  
  def initialize month, year
    @days_of_month = Date.new(year, month, 1)..Date.new(year, month, -1)
  end 

  def day weekday, schedule
    days = @days_of_month.select(&:"#{weekday}?")
    case schedule
    when :teenth
      days.find do |date|
        date.day.between?(13, 19)
      end
    when :last
      days[-1]
    else
      days[SCHEDULE.index(schedule)]
    end
  end
end
