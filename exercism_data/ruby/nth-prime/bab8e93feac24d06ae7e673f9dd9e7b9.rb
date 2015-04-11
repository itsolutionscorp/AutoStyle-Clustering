class Prime
  def self.nth(n)
    new.nth(n)
  end

  def initialize
    @primes_found = [2]
  end

  def nth(n)
    raise ArgumentError if n < 1
    return 2 if n == 1

    primes = 1 # we already marked 2 prime
    candidate = 3
    
    until primes == n
      if prime?(candidate)
        primes = primes + 1
        @primes_found.push(candidate)
      end
      candidate += 2
    end

    @primes_found.last
  end

  def prime?(n)
    primes = @primes_found.each
    prime = primes.next
    until prime * prime > n
      return false if n % prime == 0
      prime = primes.next
    end
    true
  end
end
