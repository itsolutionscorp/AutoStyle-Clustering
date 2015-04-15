require 'prime'

class PrimeFactors

  def self.for number
    Prime.prime_division(number).flat_map { |factor, power| [factor] * power  }
  end

end
