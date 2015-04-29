class Prime
  def self.nth number_of_primes_to_find
    raise ArgumentError if number_of_primes_to_find == 0
    i = 2
    found_primes = []
    while found_primes.count < number_of_primes_to_find
      found_primes << i if is_prime? i
      i += 1
    end
    found_primes.last
  end
  def self.is_prime? number
    return false if number == 1 
    return true if number == 2
    divisor = Math.sqrt(number).floor
    while divisor > 1
      return false if number % divisor == 0
      divisor -= 1
    end
    true
  end
end
