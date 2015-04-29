class Gigasecond
  
  GIGASECONDS = 1000000000
  
  def self.from(date)
    (date.to_time + GIGASECONDS).to_date
  end
  
end
