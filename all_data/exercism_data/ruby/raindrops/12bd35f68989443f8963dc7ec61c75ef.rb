#!/usr/bin/env ruby

# Exercism 4
# Raindrops
# Convert a FixNum to rainspeak based on its factors

# 3 -> Pling
# 5 -> Plang
# 7 -> Plong
# if none -> pass digits

require 'prime'

class Raindrops

  def self.convert(num)

    result = ''

    num % 3 == 0 ? result << 'Pling' : 0
    num % 5 == 0 ? result << 'Plang' : 0
    num % 7 == 0 ? result << 'Plong' : 0

    result.empty? ? num.to_s : result 

  end

end
