class Gigasecond
  attr_accessor :date
  
  GIGASECOND = 1e9
  
  def initialize(date)
    @date = giga_anniversary date
  end
  
  def giga_anniversary(date)
    return (date.to_time + GIGASECOND).to_date
  end 

end
