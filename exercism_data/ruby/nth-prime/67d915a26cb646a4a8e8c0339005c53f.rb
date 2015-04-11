class Sieve
  attr_reader :primes
  def initialize limit
    @primes = []
    potential_primes = (2..limit).to_a
    
    while prime = potential_primes.shift
      @primes << prime
      potential_primes.reject!{|n| n % prime == 0 }
    end
  end
end

class Prime
  PRIMES = Sieve.new(104743).primes
  
  def self.nth n
    fail ArgumentError unless n > 0
    PRIMES[n-1]
  end
end
