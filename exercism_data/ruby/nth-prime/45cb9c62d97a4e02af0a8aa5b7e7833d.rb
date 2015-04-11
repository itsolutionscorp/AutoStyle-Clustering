class Prime
  def prime?(n)
    i = 2
    while i < (n/2)+1
      if n % i == 0
        return false
      end
      i += 1
    end
    true
  end
  def nth(n)
    raise ArgumentError unless n > 0
    i = 0
    primes = 0
    while primes <= n
      i += 1
      if prime?(i)
        primes += 1
      end
    end
    i
  end
end
