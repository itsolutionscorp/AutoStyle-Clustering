class Gigasecond
  def self.from(date)
    time = (date.to_time.to_f) + (10**9)
    time = Time.at(time)
    gsDate = Date.parse(time.to_s)
    return gsDate
  end
end
    
