require 'prime'

class PrimeFactors
  def self.for(num)
    return [] if num == 1

    primes = Prime.each(num/2).to_a << num
    prime_factors = []

    while num > 1
      primes.each do |prime|
        if num % prime == 0
          prime_factors << prime
          num = num / prime
          break
        end
      end
    end

    prime_factors
  end
end
