require 'prime'

module Raindrops
  def self.convert number
    factors = Prime.prime_division(number).map(&:first)
    return number.to_s if ([3, 5, 7] & factors).empty?
    {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}.map {|number, sound|
      sound if factors.include? number
    }.join
  end
end
