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
