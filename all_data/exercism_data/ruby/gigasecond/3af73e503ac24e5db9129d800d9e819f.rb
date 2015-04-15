#!/usr/bin/ruby
class Gigasecond
  GIGASECOND = 10**9
  def Gigasecond.from(date)
    date += GIGASECOND
  end
end
