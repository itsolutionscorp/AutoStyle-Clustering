module Gigasecond
  def self.from (givenDate)
    #A gigasecond is one billion (10**9) seconds.
    newDate = givenDate + 10**9/(3600*24)
    return newDate
  end
end
