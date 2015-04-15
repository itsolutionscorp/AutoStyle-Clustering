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

    rain_dict = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

    rainspeak = ''
    num.prime_division.each.map{ |x| rainspeak += rain_dict[x[0]] if [3,5,7].include?(x[0]) }

    return num.to_s unless rainspeak.length >= 1

    rainspeak

  end



end
