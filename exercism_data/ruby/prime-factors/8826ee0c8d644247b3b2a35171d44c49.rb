require 'prime'

class PrimeFactors
  def self.for(num)
    Prime.find.each_with_object([]) do |prime, prime_factors|
      while num % prime == 0
        prime_factors << prime
        num /= prime
      end
      num == 1
    end
  end
end
