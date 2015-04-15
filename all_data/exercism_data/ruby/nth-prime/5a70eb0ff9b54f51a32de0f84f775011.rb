class Prime

  # return true if n is prime
  def self.is_prime(n)
    # 1 is not prime
    # Not needed to find the nth prime (since we start from 2)
    # but makes this function do what it claims to.
    if n == 1
      return false
    end

    i = 2
    until i * i > n do
      if n % i == 0
        return false
      end
      i += 1
    end

    return true
  end

  # Prime number generator
  def self.primes
    Enumerator.new do |enum|
      i = 2
      while true
        if is_prime(i)
          enum.yield i
        end
        i +=1
      end
    end
  end

  # Return the nth prime
  def self.nth(n)
    if n < 1
      raise ArgumentError, "Cannot take prime number #{n}"
    end

    primes.take(n).last
  end
end
