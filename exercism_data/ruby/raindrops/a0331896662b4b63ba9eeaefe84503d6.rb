require 'prime'

class Raindrops
  def self.convert(number)
    factors = prime_factors(number)
    result = ''

    result << 'Pling' if factors.include?(3)
    result << 'Plang' if factors.include?(5)
    result << 'Plong' if factors.include?(7)
    result << number.to_s if result == ''

    result
  end

  def self.prime_factors(number)
    number.prime_division.flatten
  end
end
