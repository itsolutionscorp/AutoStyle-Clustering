# Exercism Nth Prime solution
class Prime
  def self.nth(n)
    fail ArgumentError if n < 1
    primes = []
    i = 2
    while primes.length < n
      primes << i if no_known_prime_factor?(i, primes)
      i += 1
    end
    primes[-1]
  end

  def self.no_known_prime_factor?(x, known_primes)
    x_root = Math.sqrt(x)
    known_primes.each do |p|
      break if p > x_root
      return false if x % p == 0
    end
    true
  end
end
