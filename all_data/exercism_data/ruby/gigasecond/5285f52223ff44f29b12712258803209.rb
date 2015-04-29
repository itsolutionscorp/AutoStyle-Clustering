require 'time'

class Gigasecond
  GIGA_SECOND = 10**9

  def self.from date
    date + GIGA_SECOND
  end
end
