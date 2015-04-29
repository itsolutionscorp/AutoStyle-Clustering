class Prime
  def self.nth(num)
    if num > 0
      primes = []
      number = 2
      until primes.length == num
        primes << number if is_prime?(number)
        number += 1
      end
      primes.last
    else
      raise ArgumentError
    end
  end

  private

  def self.is_prime?(number)
    (2..(number ** 0.5)).all? { |factor| number % factor != 0 }
  end
end
