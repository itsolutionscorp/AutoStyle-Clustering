require 'date'
class Meetup
  def initialize(month, year)
    @month = month
    @year = year
  end
  attr_reader :month, :year

  def day(weekday, schedule)
    month = (Date.civil(@year, @month, 1)..Date.civil(@year, @month, -1)).map{|date| date}
    days = []
    month.each do |date|
      days << date if date.send("#{weekday}?")
    end

    case schedule.to_s
    when "first"
      days[0]
    when "second"
      days[1]
    when "third"
      days[2]
    when "fourth"
      days[3]
    when "last"
      days[-1]
    when "teenth"
      days.detect{|day| day if (13..19).member?(day.mday)}
    end
  end
end
