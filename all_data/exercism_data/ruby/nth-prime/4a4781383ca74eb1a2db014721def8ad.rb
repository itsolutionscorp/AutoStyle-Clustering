class Prime
  def self.nth(n)
    if (n <= 0)
      raise ArgumentError.new("Can't find the #{n}th prime")
    end

    primes = Array.new
    i = 2
    until (primes.length == n)
      if (isPrime(i))
        primes << i
      end
      i += 1
    end
    return primes.last
  end

  def self.isDivisible(dividend, divisor)
    if (dividend % divisor == 0)
      return true
    else
      return false
    end
  end

  def self.isPrime(num)
=begin
  WARNING! Mathematics ahead.
  Thus declares Wikipedia:
    All primes are of the form 6k+i or 6k-i, where i = -1, 0, 1, 2, 3, or 4
    6k+0, 6k+2, and 6k+4 are divisible by 2
    6k+3 is divisible by 3
    Thus we only test 2, 3, 6k-1, and 6k+1

    How many values of k do we test? Read carefully:
    When iterating through the factors of a number,
    said factors repeat themselves after sqrt(n):
    100 = (2)*50 = (4)*25 = (5)*20 = (10)*10 = (20)*5= (25)*4 = (50)*2
    Thus we test until 6k-1 reaches sqrt(n).
=end
    return num > 1 if num <= 3
    return false if (isDivisible(num,2) || isDivisible(num,3))

    k = 1
    while ((6*k-1)**2 <= num) do
      return false if (isDivisible(num,6*k+1) || isDivisible(num,6*k-1))
      k += 1
    end

    return true
  end
end
