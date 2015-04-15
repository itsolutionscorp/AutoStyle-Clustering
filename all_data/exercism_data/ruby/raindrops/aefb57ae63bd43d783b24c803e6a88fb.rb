require 'prime'

module Raindrops
  KEY_FACTORS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(number)
    factors = number.prime_division.transpose[0]
    return number.to_s unless factors
    return number.to_s unless factors.map{|x| KEY_FACTORS.keys.include?(x)}.any?
    factors.map{|x| KEY_FACTORS[x]}.join
  end
end
