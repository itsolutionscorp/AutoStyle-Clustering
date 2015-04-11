class Prime
  def self.nth nth
    fail ArgumentError if nth == 0
    i = 2
    primes = []
    while i < 1_000_000_000
      primes << i if prime?(i)
      return primes.last if primes.count == nth
      i += 1
    end
  end

  def self.prime? num
    i = 2
    while i < num
      is_divisible = ((num % i) == 0)
      return false if is_divisible
      i += 1
    end
    true
  end
end
