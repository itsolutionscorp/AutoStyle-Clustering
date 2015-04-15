class Gigasecond
  def self.from(date)
    gigaseconds = 10**9
    time = date.to_time
    time = Time.new(time.year,time.month,time.day,time.hour,time.min,time.sec) + gigaseconds
    Date.new(time.year,time.month,time.day)
  end
end 
