require 'date'
require 'time'

class Gigasecond

  def self.from(date)
    start_date = set_start_date(date)
    record_the_anniversary_date(start_date + giga_seconds)
  end

private
  def self.set_start_date(time)
    Time.new(time.year, time.month, time.day)
  end

  def self.record_the_anniversary_date(anniversary)
    Date.new(anniversary.year, anniversary.month, anniversary.day)
  end

  def self.giga_seconds
    10**9
  end

end
