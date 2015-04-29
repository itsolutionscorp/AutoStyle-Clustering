#!/usr/bin/env ruby

class Raindrops
  def self.convert(num)
    retval = ''
    retval << 'Pling' if num % 3 == 0
    retval << 'Plang' if num % 5 == 0
    retval << 'Plong' if num % 7 == 0

    retval.empty? ? num.to_s : retval
  end
end
