require 'date'
require 'time'

class Gigasecond
  def self.from date
    gs_anniversary = date.to_time + 10**9
    self.time_to_date gs_anniversary
  end

  def self.time_to_date time
    Date.new(time.year, time.month, time.day)
  end
end
