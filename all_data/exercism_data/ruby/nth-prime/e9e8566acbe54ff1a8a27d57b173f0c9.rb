module Prime
  @@known_primes = [2,3]

  def self.nth(number)
    raise ArgumentError if number == 0

    i = @@known_primes.max
    while @@known_primes.length < number
      is_prime?(i)
      i += 2
    end
    @@known_primes[number - 1]
  end

  def self.is_prime?(number)
    return true if @@known_primes.include? number
    (2..(Math.sqrt(number).round + 1)).each do | n |
      return false if number % n == 0
    end

    @@known_primes << number
    true
  end
end
