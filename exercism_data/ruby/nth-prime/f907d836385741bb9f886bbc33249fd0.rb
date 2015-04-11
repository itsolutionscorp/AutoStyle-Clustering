# require 'Prime'

class Prime
  PRIMES = [2]
  @next_num = PRIMES[-1] + 1

  def self.is_0(num)
    raise ArgumentError if num == 0
  end

  def self.is_prime(num)
    limit = Math.sqrt(num)
    2.upto(limit).any? do |n|
      return false if num % n == 0
    end

    true
  end

  def self.nth(num)
    is_0(num)

    while PRIMES[num - 1] == nil
      PRIMES << @next_num if is_prime(@next_num)
      @next_num += 1
    end
    PRIMES[num - 1]
  end
end
