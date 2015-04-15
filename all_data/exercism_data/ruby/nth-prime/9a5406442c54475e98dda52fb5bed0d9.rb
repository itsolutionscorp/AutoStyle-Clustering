class Prime
  def prime?(n)
    (2..Math.sqrt(n)).none? { |i| n % i == 0 }
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
