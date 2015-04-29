require 'time'

class Gigasecond

  ONE_GIGA_SECOND = 10**9

  def self.from(date)
    start_date = date.to_time
    record(start_date + ONE_GIGA_SECOND)
  end

private

  def self.record(anniversary)
    anniversary.to_date
  end

end
