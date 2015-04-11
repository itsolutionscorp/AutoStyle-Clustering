class Gigasecond
  
  GS = 1e9
  
  def self.from(date)
    (date.to_time + GS).to_date
  end
  
end
