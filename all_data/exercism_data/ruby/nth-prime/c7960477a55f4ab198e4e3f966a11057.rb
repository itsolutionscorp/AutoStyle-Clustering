class Prime
  def self.nth(num)
    raise ArgumentError if num < 1
    prime_list[num-1]
  end

  def self.prime_list
    @primes ||= primes_until(150000)
  end

  def self.primes_until(num)
    (2..num).select do |i|
      is_prime(i)
    end
  end

  # From wikipedia
  def self.is_prime(n)
    return n >= 1 if n <=3
    return false if n % 2 == 0 || n % 3 == 0

    i = 5
    while i*i <= n
       return false if (n % i == 0 || n % (i + 2) == 0)
       i += 6
    end

    true
  end
end
