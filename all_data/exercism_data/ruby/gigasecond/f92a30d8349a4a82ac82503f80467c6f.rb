require 'date'
require 'time'
class Gigasecond

  def self.from(start_date)    
    return start_date + 11574
  end
   
  if __FILE__ == $0
    puts Gigasecond.from(Date.new(1969, 10, 2))    
  end
end
