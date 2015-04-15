require "prime"

class PrimeFactors
  class << self
      
    def for number
      primes = Prime.prime_division number

      primes.each_with_object [] do |(prime_number, multiplicity), array|
        multiplicity.times { array << prime_number }
      end
    end

  end
end
