require 'prime'
class Prime
  def self.nth(position)
    raise ArgumentError if position == 0
    num = 0
    primes = []
      until primes.length == position
        primes << num if num.prime?
        num += 1
      end
    primes[-1]
  end
end
