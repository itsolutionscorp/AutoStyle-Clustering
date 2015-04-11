require 'prime'

class Raindrops

  FACTOR_STRINGS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(num)
    prime_factors = Prime.prime_division(num)
    factor_string = factors_to_string(prime_factors)
    factor_string.empty? ? "#{num}" : factor_string
  end

  private

  def self.factors_to_string(prime_factors)
    prime_factors.flatten.map do |factor|
      FACTOR_STRINGS[factor]
    end.join
  end
end
