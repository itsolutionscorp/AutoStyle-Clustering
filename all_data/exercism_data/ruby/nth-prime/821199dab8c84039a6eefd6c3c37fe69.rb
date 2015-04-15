class Prime
  def self.nth(num)
    primes = []
    number = 2
    if num > 0
      until primes.length == num
        primes << number if is_prime?(number)
        number += 1
      end
    else
      raise ArgumentError
    end
    primes.last
  end

  private

  def self.is_prime?(number)
    (2..(number ** 0.5)).all? { |factor| number % factor != 0 }
  end
end
