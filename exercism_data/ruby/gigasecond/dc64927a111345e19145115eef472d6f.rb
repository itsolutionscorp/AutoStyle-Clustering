#!/usr/bin/ruby

require 'date'

class Gigasecond
  def self.from(inputdate)
    days = (10**9)/86400 #convert gigasecond into days by dividing seconds in a day
    return ((inputdate) + days)
  end
end
