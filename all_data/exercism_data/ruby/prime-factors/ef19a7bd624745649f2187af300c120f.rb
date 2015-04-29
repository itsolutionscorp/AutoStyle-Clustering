class PrimeFactors
  def self.for(num)
    primes = []
    return primes if num == 1
    factor = 2
    while num != 1
      while num % factor == 0
        primes << factor
        num = num / factor
      end
      factor = factor + 1
    end
    primes
  end
end
