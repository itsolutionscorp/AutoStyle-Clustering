require 'date'

class Gigasecond 

  def self.from(date)
    giga_second = (10**9)
    Time.at(date.to_time.to_i + giga_second).to_date
  end

end
