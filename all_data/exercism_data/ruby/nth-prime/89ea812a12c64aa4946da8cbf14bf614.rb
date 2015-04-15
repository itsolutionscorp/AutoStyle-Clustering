class Prime

  def self.nth (n)
    @@primes = []
    if n <= 0
      raise ArgumentError, "Requires integer greater than 0"
    end
    i = 2
    loop do
      check_prime(i)
      return @@primes[-1] if @@primes.length == n
      i+=1
    end
  end

  def self.check_prime (num)
    @@primes.each do |prime|
      if num % prime == 0
        return false
      end
    end
    @@primes << num
  end

end
