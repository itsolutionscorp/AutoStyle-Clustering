class Gigasecond
  
  def self.from(date)
    gigaseconds_in_days = (10**9) / 60 / 60 / 24
    date + gigaseconds_in_days
  end
  
end
