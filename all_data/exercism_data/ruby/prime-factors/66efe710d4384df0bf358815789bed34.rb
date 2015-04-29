require 'prime'

class PrimeFactors
  # Using trial division
  # See: http://en.wikipedia.org/wiki/Trial_division
  def self.for(number)
    return [] if number == 1

    primes = Prime.first(number**0.5 + 1)
    prime_factors = []

    primes.each do |prime|
      break if prime * prime > number

      while number % prime == 0
        prime_factors << prime
        number /= prime
      end
    end

    prime_factors << number if number > 1

    return prime_factors
  end
end
