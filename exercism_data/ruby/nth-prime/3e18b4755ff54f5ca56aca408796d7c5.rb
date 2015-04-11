# require 'Prime'

class Prime
  @@primes = [2]

  def self.prime?(num)
    limit = Math.sqrt(num)
    (2..limit).any? do |n|
      return false if num % n == 0
    end

    true
  end

  def self.nth(num)
    raise ArgumentError if num == 0

    next_num = @@primes[-1] + 1
    while @@primes[num - 1].nil?
      @@primes << next_num if prime?(next_num)
      next_num += 1
    end
    @@primes[num - 1]
  end
end
