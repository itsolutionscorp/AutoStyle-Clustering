class Gigasecond
  attr_accessor :start_date
  
  def initialize(start_date)
  	@start_date = start_date
  end

  def date
  	a = start_date.to_time.to_i + 10**9
  	Time.at(a)
  end
end
