#gigasecond is one billion (10**9) seconds.


class Gigasecond
  
  #method assumes UTC is input 
  def self.from(utc_time)
    utc_time + 10**9
  end

end
