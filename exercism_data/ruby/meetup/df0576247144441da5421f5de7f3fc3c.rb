require 'date'

class Meetup
  def initialize(year, month)
    @time = 1.upto(Date.new(month, year, -1).day).map do |d|
      Date.new(month, year, d)
    end
  end

  def teenth(days)
    days.select { |d| d.day > 12 && d.day < 20 }
  end

  def weekday(days, i)
    days.select { |d| d.wday == i }
  end

  def daynum(day)
    return 0 if day.start_with?("sun")
    return 1 if day.start_with?("mon")
    return 2 if day.start_with?("tues")
    return 3 if day.start_with?("wed")
    return 4 if day.start_with?("thu")
    return 5 if day.start_with?("fri")
    return 6 if day.start_with?("sat")
  end

  def method_missing(m)
    if m.to_s.end_with?("teenth")
      teenth(weekday(@time, daynum(m.to_s.gsub("teenth","")))).first
    elsif m.to_s.start_with?("first_")
      weekday(@time, daynum(m.to_s.gsub("first_",""))).first
    elsif m.to_s.start_with?("second_")
      weekday(@time, daynum(m.to_s.gsub("second_","")))[1]
    elsif m.to_s.start_with?("third_")
      weekday(@time, daynum(m.to_s.gsub("third_","")))[2]
    elsif m.to_s.start_with?("fourth_")
      weekday(@time, daynum(m.to_s.gsub("fourth_","")))[3]
    elsif m.to_s.start_with?("last_")
      weekday(@time, daynum(m.to_s.gsub("last_",""))).last
    end
  end
end
