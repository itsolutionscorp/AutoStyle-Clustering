#!/usr/bin/env ruby -w
# Approach with magic number: 2nd iteration

class Gigasecond

  DAYS_PER_GIGASECOND = 11574 # 10**9 / (60 * 60 * 24)

  def self.from date
    return date + DAYS_PER_GIGASECOND
  end
end
