#!/usr/bin/env ruby

module Year
  def self.leap?(year)
    return false unless year % 4   == 0
    return true  unless year % 100 == 0
    return false unless year % 400 == 0
    true
  end
end