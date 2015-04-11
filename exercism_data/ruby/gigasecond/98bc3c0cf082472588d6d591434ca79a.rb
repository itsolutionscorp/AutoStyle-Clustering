require 'date'

class Gigasecond

  GIGASECOND_IN_DAYS = 11574

  def self.from(date)
    date + GIGASECOND_IN_DAYS
  end
end
