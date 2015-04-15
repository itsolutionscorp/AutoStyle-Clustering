require 'time'

class Gigasecond

  def self.from(date)
    start_date = date.to_time
    record_the_anniversary_date(start_date + giga_seconds)
  end

private

  def self.record_the_anniversary_date(anniversary)
    anniversary.to_date
  end

  def self.giga_seconds
    10**9
  end

end
