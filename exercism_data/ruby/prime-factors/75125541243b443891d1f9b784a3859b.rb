require 'prime'

class PrimeFactors
  def self.for(n)
    @prime_factors = []
    Prime.prime_division(n).each do |factor_exponent_pair|
      factor_exponent_pair[1].times { @prime_factors << factor_exponent_pair[0]}
    end
    @prime_factors
  end
end
