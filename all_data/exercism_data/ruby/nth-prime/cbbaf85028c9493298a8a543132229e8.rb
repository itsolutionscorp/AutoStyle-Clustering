class Prime
  PRIMES = []

  def self.nth(num)
    raise ArgumentError if num < 2
    generate_primes(num)
    PRIMES[num - 1]
  end

  def self.generate_primes(num)
    while PRIMES.length < num
      PRIMES << next_prime
    end
  end

  def self.next_prime
    if PRIMES.last
      (PRIMES.last + 1..PRIMES.last * 2).each do |n|
        return n if !PRIMES.any? { |p| n % p == 0 }
      end
    else
      2
    end
  end
end
