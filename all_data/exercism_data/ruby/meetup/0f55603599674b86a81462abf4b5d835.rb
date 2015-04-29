class Meetup
  def initialize(month, year)
    @month = month
    @year = year
  end
 
  def day(weekday, method)
    candidates = days_of_month(weekday)
    
    case method
    when :teenth then candidates.find{ |d| is_teenth(d) }
    when :first then candidates.first
    when :second then candidates[1]
    when :third then candidates[2]
    when :fourth then candidates[3]
    when :last then candidates.last
    end
  end
  
  private 
    
  def is_teenth(date)
    (13..19) === date.day 
  end
  
  def days_of_month(weekday)
    first_day = Date.new(@year, @month)
    weekday_method = "#{weekday}?".to_sym
    
    (first_day...first_day.next_month)
      .select{ |d| d.send weekday_method }
  end
end
