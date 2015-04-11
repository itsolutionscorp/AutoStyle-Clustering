class PrimeFactors
  def self.for(num)
    primes = []

    candidate = 2
    while num > 1
      while num % candidate == 0
        primes << candidate
        num /= candidate
      end
      candidate += 1
    end
    return primes
  end
end
