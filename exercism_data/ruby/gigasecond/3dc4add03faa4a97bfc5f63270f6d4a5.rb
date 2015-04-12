require 'date'
require 'time'

class Gigasecond
  DAYS_IN_A_GIGASECOND = 10**9/86400

  def self.from(date)
    date + DAYS_IN_A_GIGASECOND
  end
end