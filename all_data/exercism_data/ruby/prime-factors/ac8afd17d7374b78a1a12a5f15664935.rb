require 'prime'

class PrimeFactors
  def self.for(int)
    Prime.prime_division(int).each_with_object([]) do |(prime, exponent), primes|
      exponent.times { primes << prime }
    end
  end
end
