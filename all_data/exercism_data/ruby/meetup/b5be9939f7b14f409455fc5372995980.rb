require 'date'

class Meetup
  def initialize month, year
    @month = month
    @year = year
  end

  def day weekday, schedule
    d = Date.new(@year, @month)
    d += 1 until Date::DAYNAMES[d.wday].downcase == weekday.to_s
    case schedule
    when :first then d
    when :second then d + 7
    when :third then d + 14
    when :fourth then d + 21
    when :last then (d + 28).month == d.month ? d + 28 : d + 21
    else d.mday < 6 ? d + 14 : d + 7 
    end
  end
end
