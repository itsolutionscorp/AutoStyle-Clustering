class Sieve
  def initialize max_prime
    range = (0..max_prime)
    @primes = range.to_a
    
    range.each{|n|
      @primes[n] = nil if n < 2
      next unless @primes[n]

      multiples = (n*2..max_prime).step(n)
      multiples.each {|m| @primes[m] = nil }
    }
    @primes.compact!
  end

  attr_reader :primes
end
