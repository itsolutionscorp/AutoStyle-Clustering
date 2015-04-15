require 'prime'
class PrimeFactors

  def self.for(num)
    primes = []
    num.prime_division.each do |prime_set|
      prime_set[1].times {
        primes << prime_set[0]
      }
    end
    primes
  end
end
