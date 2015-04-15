class Sieve

  attr_reader :primes
  
  def initialize(max)
    @primes = sieve(max)
  end

  private

  def sieve(max)
    
    primes = (2..max).to_a

    for i in 0..primes.size
      break if primes[i] > Math.sqrt(max).floor
      primes.delete_if {|n| n>= primes[i]**2 and n % primes[i] == 0}
    end

    primes

  end

end
