require 'prime'

class PrimeFactors
  def self.for number
    factors = []
    Prime.each(ubound = Math.sqrt(number)) do |prime|
      while number % prime == 0
        factors << prime
        number /= prime
      end
    end
    factors << number unless number == 1
    return factors
  end
end
