class Prime
  def self.nth(num)
    new(num).nth
  end

  attr_reader :num, :primes

  def initialize(num)
    raise ArgumentError if num == 0
    @num = num
    @primes = [2]
  end

  def nth
    find_prime while primes.length < num
    primes[num - 1]
  end

  def find_prime
    current = primes.last + 1
    current += 1 while divisible_by_primes(current)
    primes << current
  end

  def divisible_by_primes(num)
    primes.any? {|prime| num % prime == 0 }
  end
end
