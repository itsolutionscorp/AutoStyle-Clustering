require 'date'

class Gigasecond

  def self.from(date)
    t = date.to_time
    tnum = t.to_i + 10**9    
    Time.at(tnum).to_date
  end
  
end
