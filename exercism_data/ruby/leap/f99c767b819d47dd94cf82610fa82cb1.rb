#!/usr/bin/env ruby
# encoding: utf-8
# Leap
class Year
  # This is a static method
  def self.leap?(year)
    # By default the flag is false
    is_leap = false
    # If the year parameter is a leap year, we change the flag to true.
    year % 4 == 0 && (year % 100 != 0 || year % 400 == 0) ? is_leap = true : #
    is_leap # The final result
  end
end
