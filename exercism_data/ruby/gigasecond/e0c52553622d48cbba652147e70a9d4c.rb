module Gigasecond
  def self.from (givenDate)
    #A gigasecond is one billion (10**9) seconds.
    givenDate + 10**9/(3600*24)
  end
end
