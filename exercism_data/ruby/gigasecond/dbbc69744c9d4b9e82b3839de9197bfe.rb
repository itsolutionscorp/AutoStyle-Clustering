require 'date'
require 'time'

class Gigasecond

  GIGASECONDs_IN_DAYS = 10**9 / (60 * 60 * 24)  # 1 day = 86,400 seconds

  def self.from(date)
    date + GIGASECONDs_IN_DAYS
  end

end
