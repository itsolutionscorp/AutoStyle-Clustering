#!/usr/bin/env ruby -w

class Gigasecond
  def Gigasecond.from(date)
    diff = 10**9 / 86400
    return date + diff
  end
end
