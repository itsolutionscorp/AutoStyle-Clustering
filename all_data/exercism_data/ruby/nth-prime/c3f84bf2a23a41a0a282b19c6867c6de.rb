require "prime"

class Prime
  def self.nth(n)
    fail ArgumentError if n <= 0
    EratosthenesSieve.instance.get_nth_prime(n - 1)
  end
end
