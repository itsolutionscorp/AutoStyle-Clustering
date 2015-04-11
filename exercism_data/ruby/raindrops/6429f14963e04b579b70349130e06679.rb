require 'prime'

module Raindrops
  CONVERSION = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert number
    factors = number.prime_division.map(&:first) & [3, 5, 7]
    return number.to_s if factors.empty?

    factors.map { |k| CONVERSION[k] }.join
  end
end
