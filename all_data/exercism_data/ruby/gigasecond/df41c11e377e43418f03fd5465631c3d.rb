require 'date'

class Gigasecond
  GIGA_SECOND = 10**9
  SECONDS_PER_DAY = 60 * 60 * 24

  def self.from(date)
    date + GIGA_SECOND / SECONDS_PER_DAY
  end
end
