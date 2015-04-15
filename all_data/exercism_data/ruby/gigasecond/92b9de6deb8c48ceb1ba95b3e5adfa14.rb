require 'date'

class Gigasecond
  ONE_GIGASECOND = 10**9

  def self.from(date)
    time = date.to_time + ONE_GIGASECOND
    Date.new(time.year, time.month, time.day)
  end
end
