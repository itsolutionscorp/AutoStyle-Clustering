module Prime
  @@primes = [ 2, 3, 5, 7,11,13,17,19,23,29,
              31,37,41,43,47,53,59,61,67,71,
              73,79,83,89,97]

  def self.nth(n_from_1)
    raise(ArgumentError, "Illegal prime count") if n_from_1 <= 0

    n = n_from_1 - 1
    @@primes[n] || get_nth_prime(n)
  end

  private
  def self.get_nth_prime(n)
    candidate = @@primes.last + 2
    loop do
      return @@primes[n] unless @@primes[n].nil?

      @@primes.push(candidate) unless any_factors? candidate
      candidate += 2
    end
  end

  def self.any_factors?(i)
    @@primes.each do |prime|
        return false if prime > Math.sqrt(i)
        return true if factor?(i, prime)
    end
  end

  def self.factor?(i, prime)
    i >= prime && i % prime == 0
  end
end
