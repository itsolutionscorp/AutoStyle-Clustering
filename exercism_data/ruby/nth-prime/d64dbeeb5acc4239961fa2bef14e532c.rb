class Prime
  def self.nth(n)
    if n < 1
      raise ArgumentError
    end

    primes = []
    i = 2
    until primes.length == n
      primes << i if self.is_prime?(i)
      i += 1
    end

    primes[n - 1]
  end

  def self.is_prime?(num)
    (2..Math.sqrt(num).to_i).each do |x|
      return false if num % x == 0
    end
    true
  end
end
