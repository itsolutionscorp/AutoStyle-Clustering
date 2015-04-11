#!/usr/bin/env ruby

require 'date'

class Gigasecond
  def self.from(date)
    date + (10**9)
  end
end
