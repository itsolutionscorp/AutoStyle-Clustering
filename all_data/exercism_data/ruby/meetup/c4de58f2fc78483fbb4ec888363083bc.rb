require 'date'
class Meetup
  def initialize month, year
    @month = Date.new year, month
  end
  
  def day weekday, schedule
    schedule_day schedule, days_that_are(weekday)
  end
  
  private
  def month_range
    @month_range ||= @month...@month.next_month
  end
  
  def days_that_are weekday
    month_range.select{|day| day.send("#{weekday}?")}
  end
  
  def schedule_day schedule, days
    case schedule
    when :teenth
      teenth_day days
    when :last
      days.last
    else
      days[th_index schedule]
    end
  end
  
  def teenth_day days
    days.find{|day| (13..19).include? day.mday }
  end
  
  def th_keys
    @th_keys ||= %i(first second third fourth)
  end
  
  def th_index key
    th_keys.index(key).to_i
  end
end
