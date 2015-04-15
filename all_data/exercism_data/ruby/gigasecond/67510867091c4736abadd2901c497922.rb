require 'pry'

class Gigasecond

  def self.from(time)
    time_plus_gigasecond = time.to_i + (10**9)
    Time.at(time_plus_gigasecond)
  end

end
