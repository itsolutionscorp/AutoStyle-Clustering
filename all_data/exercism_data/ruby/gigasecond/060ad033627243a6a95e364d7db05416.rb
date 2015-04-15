module Gigasecond

  def self.from(date)
    if date.class == Date
      date = date.to_time
    end
    
    date += 1_000_000_000
    date.to_date
  end

end
