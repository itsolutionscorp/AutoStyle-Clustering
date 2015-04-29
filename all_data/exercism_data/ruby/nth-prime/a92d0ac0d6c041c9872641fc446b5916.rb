class Prime
  def self.nth nth
    fail ArgumentError if nth == 0
    i = 2
    primes = []
    until primes.count == nth
      primes << i if prime?(i)
      i += 1
    end
    primes.last
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
