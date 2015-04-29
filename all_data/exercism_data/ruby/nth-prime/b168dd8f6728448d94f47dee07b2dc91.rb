class Fixnum
  def is_prime?
    return false if self<=1
    for i in 2..(Math.sqrt(self))
        return false if self%i==0
    end
    return true
  end
end


class Prime
  def self.nth(n)
      raise ArgumentError if n<=0

      primes = [2]                        # 2 is the first prime number
      number = primes.last

      while primes.length < n
        number += 1
        primes << number if number.is_prime?
      end

      return primes.last
  end
end
