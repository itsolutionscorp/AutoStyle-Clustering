require 'date'
require 'time'

class Gigasecond
  GIGASECOND = 10**9;

  def self.from(date)
    from_time(date.to_time).to_date
  end

  def self.from_time(time)
    time + GIGASECOND
  end
end
