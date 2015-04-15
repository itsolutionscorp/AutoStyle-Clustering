require "prime"

class PrimeFactors
  def self.for(n)
    return [] if n == 1
    first_prime_factor = Prime.find { |prime| n % prime == 0 }
    [first_prime_factor] + self.for(n / first_prime_factor)
  end
end
