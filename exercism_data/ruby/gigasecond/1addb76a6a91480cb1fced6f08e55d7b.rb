class Gigasecond
  DAYS_DIFF = 11574
  
  def initialize(startdate)
    @startdate = startdate
  end

  def date
    @startdate + DAYS_DIFF
  end
end 
