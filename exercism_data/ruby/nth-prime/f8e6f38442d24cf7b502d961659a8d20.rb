#!/usr/bin/env ruby

# Exercism 8
# Nth prime
# Write program to determine Nth prime of number

require 'prime'

class Prime

  def nth(int)

    raise ArgumentError if int == 0
    
    Prime.first(int).last

  end

end
