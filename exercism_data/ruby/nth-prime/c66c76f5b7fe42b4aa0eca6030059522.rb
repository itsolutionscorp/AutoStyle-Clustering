class Prime
  def self.nth(num)
    primes = []
    number = 2
    if num > 0
      until primes.length == num
        primes << number if (2..(number ** 0.5)).all? { |factor| number % factor != 0 }
        number += 1
      end
    else
      raise ArgumentError
    end
    primes.last
  end
end
