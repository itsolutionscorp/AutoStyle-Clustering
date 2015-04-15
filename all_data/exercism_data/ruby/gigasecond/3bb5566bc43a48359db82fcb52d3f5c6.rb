require 'date'

class Gigasecond
  
  GIGA_SECONDS = 10**9

  def self.from(date)
    (Time.at(date.to_time.to_i + GIGA_SECONDS)).to_date
  end
end
