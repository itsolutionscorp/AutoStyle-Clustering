class Gigasecond
  attr_accessor :date
  
  GIGASECOND = 1e9
  
  def initialize(date)
    @date = date
  end
  
  def date
    return (@date.to_time + GIGASECOND).to_date
  end 

end
