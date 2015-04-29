module Gigasecond
  require 'date'
  def self.from (givenDate)
    #A gigasecond is one billion (10**9) seconds.
    days = 10**9/(3600*24)
    #secondsRemain = 10**9%(3600*24)
    #hoursRemain = secondsRemain/3600
    #secondsRemain = secondsRemain%3600
    newDate = givenDate + days
    puts newDate
    return newDate
  end
end
