class Prime
  def self.nth(n)
    if n < 1
      raise ArgumentError
    end

    prime_number = 2
    primes_so_far = 1

    until primes_so_far == n
      prime_number += 1
      if prime_number.is_prime?
        primes_so_far += 1
      end
    end
    prime_number
  end
end

class Fixnum
  def is_prime?
    (Math.sqrt(self).to_int - 1).times do |number|
      if self % (number + 2) == 0
        return false
      end
    end
    true
  end
end
