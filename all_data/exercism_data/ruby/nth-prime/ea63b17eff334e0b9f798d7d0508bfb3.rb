class Prime
  def self.nth(n)
    fail ArgumentError if n < 1

    primes = [2]
    candidate = 3

    while primes.size <= n
      unless primes.any? { |p| candidate % p == 0 }
        primes << candidate
      end

      candidate += 2 # Skip even numbers
    end

    return primes[n-1]
  end
end
