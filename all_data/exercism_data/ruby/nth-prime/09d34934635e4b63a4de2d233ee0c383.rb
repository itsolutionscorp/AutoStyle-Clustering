class Prime

  def self.is_prime?(num)
    divisors = (2..num-1)
    divisors.each do |i|
      return false if num % i == 0
    end
    return true
  end

  def self.generate_primes(num)
    primes = [2]
    start_count = 3
    until primes.length == num
      primes << start_count if is_prime?(start_count)
      start_count += 1
    end
    primes.last
  end

  def self.nth(num)
    raise ArgumentError if num == 0
    generate_primes(num)
  end

end
