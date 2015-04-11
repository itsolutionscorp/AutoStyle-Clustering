class Gigasecond
  def self.from(date)
    if date.is_a?(Time)
      date = Date.parse(date.to_s) + 1
    end  
    gigaseconds_in_days = (10**9)/(60*60*24)
    date + gigaseconds_in_days
  end
end  
