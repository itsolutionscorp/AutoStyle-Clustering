class Prime
  @@primes = [2]

  def self.nth(n)
    raise ArgumentError if n <= 0

    until @@primes.length >= n
      prime = @@primes.last

      until is_prime(prime)
        prime += 1
      end

      @@primes << prime
    end

    @@primes[n-1]
  end

  private

  def self.is_prime(i)
    @@primes.all? { |prime| i % prime != 0 }
  end
end
