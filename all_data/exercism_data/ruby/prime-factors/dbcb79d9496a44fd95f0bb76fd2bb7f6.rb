require 'prime'

class PrimeFactors
  def self.for num
    num.prime_factors
  end
end

class Integer
  def prime_factors
    factors = []
    result = self

    Prime.each do |prime|
      while result % prime == 0
        factors << prime
        result /= prime
      end
      break if result <= prime
    end

    factors
  end
end
