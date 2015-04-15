class Gigasecond
  GIGASECOND_IN_DAYS = 11_574
  
  def initialize(date)
    @date = date
  end

  def date
    @date +  GIGASECOND_IN_DAYS
  end
end
