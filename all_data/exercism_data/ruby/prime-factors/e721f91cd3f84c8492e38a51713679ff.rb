require 'prime'

# Computes the prime factors of a given natural number.
class PrimeFactors
  def self.for(number)
    primes = (2..number).select(&:prime?)

    [].tap do |prime_factors|
      while number > 1
        primes.each do |prime|
          prime_factors << prime && number /= prime if number % prime == 0
        end
      end
    end
  end
end
