class Prime
  def self.nth(factor)
    raise ArgumentError if factor == 0

    #All non-primes are divisible by a prime number.
    #Hence only need to check against existing primes.
    primes = []
    count = 2
    while primes.length < factor
      primes << count unless primes.any?{|n| count % n == 0 }
      count += 1
    end
    primes[-1]
  end
end
