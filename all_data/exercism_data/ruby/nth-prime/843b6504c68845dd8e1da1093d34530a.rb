class Prime
  def self.nth m
    fail ArgumentError if m == 0
    primes = []
    n = 1
    until primes.size > m
      primes << n if primes.each_with_object [] do |i, divisors|
        divisors << i if n % i == 0
      end.length < 2
      n += 1
    end
    primes.last
  end
end
