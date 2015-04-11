require 'date'

class Gigasecond
  GIGASECOND = 10**9
  NUMBER_OF_SECONDS_PER_DAY = 60*60*24

  def self.from(date)
    daysToAdd = GIGASECOND/NUMBER_OF_SECONDS_PER_DAY
    return date + daysToAdd
  end
end
