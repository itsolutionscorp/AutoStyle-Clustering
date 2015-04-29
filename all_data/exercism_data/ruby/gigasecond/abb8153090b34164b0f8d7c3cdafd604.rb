class Gigasecond
  
  def self.from date
    self.new(date).date
  end
  
  def initialize date
    @time = date.to_time + 10 ** 9
  end
  
  def date
    @time.to_date
  end
  
end
