class Prime

  @@primes = [2, 3, 5, 7, 11, 13]

  def self.nth(n)
    raise ArgumentError if n <= 0
    # loop checks for nth prime in primes array.  Return it if present
    # otherwise find the next prime and add it to array
    loop do
      if @@primes[n-1]
        return @@primes[n-1]
      else
        @@primes << next_prime(@@primes[-1])
      end
    end
  end

  def self.next_prime(num)
    if num%2 == 0
      test_val = num + 1
    else
      test_val = num + 2
    end
    loop do
      prime_flag = true
      @@primes.each do |prime|
        if test_val % prime == 0
          prime_flag = false
          break
        end
      end
      if prime_flag
        return test_val
      else
        test_val += 2
      end
    end
  end
end
