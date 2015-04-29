class Gigasecond
  GIGASECOND_IN_DAYS = (10**9)/(60*60*24)

  def self.from(date)
    if date.is_a?(Time)
      date = Date.parse(date.to_s) + 1
    end  
    date + GIGASECOND_IN_DAYS
  end
end  
