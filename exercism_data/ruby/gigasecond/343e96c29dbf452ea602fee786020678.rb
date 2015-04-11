require 'date'
require 'time'

class Gigasecond

  def self.from(date)
    Time.at(date.to_time.tv_sec + 10**9).to_date
  end

end


p Gigasecond.from Date.new(2011, 4, 25)
