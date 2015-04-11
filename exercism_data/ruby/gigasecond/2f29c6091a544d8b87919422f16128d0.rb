require 'date'

class Gigasecond
  GIGASECOND_IN_DAYS = 10**9 / 24 / 3600

  def self.from(date)
    date + GIGASECOND_IN_DAYS
  end
end
