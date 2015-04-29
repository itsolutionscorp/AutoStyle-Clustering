class Prime
  def self.nth(factor)
    raise ArgumentError if factor = 0
    primes = []
    count = 2
    while primes.length < factor
      primes << count unless (2...count).any? { |e| count % e == 0 }
      count += 1
    end
    primes[-1]
  end
end
