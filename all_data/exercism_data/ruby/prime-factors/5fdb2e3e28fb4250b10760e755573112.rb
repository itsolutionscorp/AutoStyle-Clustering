require "prime"

class PrimeFactors
  class << self
      
    def for number
      primes = Prime.prime_division number

      primes.each_with_object [] do |pair, array|
        pair.last.times do 
          array << pair.first 
        end
      end
    end

  end
end
