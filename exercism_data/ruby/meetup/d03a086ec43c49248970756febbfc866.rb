require 'date'
class Meetup
  
  
  def initialize(month,year)
    @month = month
    @year = year
  end
  
  def day(day, schedule)   
    case schedule
    when :teenth
      return teenth(day)
    when :first
      return day_at_schedule(day,1)
    when :second
      return day_at_schedule(day,2)
    when :third
      return day_at_schedule(day,3)
    when :fourth
      return day_at_schedule(day,4)
    when :last
      return last(day)
    else
      puts "You can't even use a computer!"
    end     
  end
    
  def day_at_schedule(day, max_count)
    i = 1
    count = 0
    loop do
      d = Date.new(@year, @month, i)
      if Date::DAYNAMES[d.wday].downcase == day.to_s 
        count +=1
        return d if count == max_count
      end  
      i += 1
      break if d.month != @month
    end
  end

  def last(day)
    the_year = @year
    next_month = @month+1
    if next_month == 13 then 
      next_month = 1
      the_year = the_year+1 
    end
    d = Date.new(the_year, next_month, 1)-1
    loop do
      return d if Date::DAYNAMES[d.wday].downcase == day.to_s 
      d = d-1 
    end
  end
  
  def teenth(day)
    i = 13
    loop do
      d = Date.new(@year, @month, i)
      return d if Date::DAYNAMES[d.wday].downcase == day.to_s
      i += 1
      break if i > 19
    end
  end
  
end
