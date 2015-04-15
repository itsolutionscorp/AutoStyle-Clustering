class Prime
  PRIMES = [2, 3]

  def self.nth(index)
    raise ArgumentError if index < 1
    return PRIMES[index - 1] if PRIMES.length >= index
    generate_primes_up_to(index)
  end

  def self.generate_primes_up_to(index)
    candidate = PRIMES.last + 2
    counter = PRIMES.length
    while counter < index do
      if is_prime?(candidate)
        PRIMES << candidate
        counter += 1
      end
      candidate += 2
    end
    PRIMES[index - 1]
  end

  def self.is_prime?(num)
    return false if PRIMES.any?{|p| num % p == 0 }
    factor = PRIMES.last + 2
    while factor <= Math.sqrt(num) do
      return false if num % p == 0
      factor += 2
    end
    return true
  end

end
