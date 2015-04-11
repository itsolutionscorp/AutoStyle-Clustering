class Prime
  def self.nth(n)
    fail ArgumentError if n < 1

    return @primes[n - 1] if @primes && @primes.length >= n

    @primes = [2]
    i = 3
    until @primes.length == n
      @primes << i if self.is_prime?(i)
      i += 2
    end

    @primes[n - 1]
  end

  def self.is_prime?(num)
    upper_test_limit = Math.sqrt(num)
    @primes.each do |x|
      break if x > upper_test_limit
      return false if num % x == 0
    end
    true
  end
end
