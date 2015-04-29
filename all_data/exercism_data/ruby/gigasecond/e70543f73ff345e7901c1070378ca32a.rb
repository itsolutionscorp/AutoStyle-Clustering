require 'date'

class Gigasecond

  def self.from(date)
    giga_days = (10**9)/(60 * 60 * 24)
    date + giga_days
  end

end
