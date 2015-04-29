class Prime
  def self.nth(n)
    raise ArgumentError if n <= 0

    primes = []

    i = 0
    loop do
      i += 1

      primes << i if is_prime?(primes, i)

      return primes.last if primes.length == n
    end
  end

  def self.is_prime?(primes, i)
    return false if i < 2
    return true  if i == 2
    primes.all? { |prime| i % prime != 0 }
  end
end
