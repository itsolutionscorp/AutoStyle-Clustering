require 'prime'

class PrimeFactors
  def self.for(number)
    primes = (2..number).select { |n| n.prime? }

    [].tap do |prime_factors|
      while number > 1
        primes.each do |prime|
          if number % prime == 0
            prime_factors << prime
            number /= prime
          end
        end
      end
    end
  end
end
