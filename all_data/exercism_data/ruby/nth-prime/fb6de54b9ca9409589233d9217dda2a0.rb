module Prime

  def self.nth n
    raise ArgumentError if n == 0
    index = 1
    primes = [2]
    while primes.length < n
      index += 2
      primes << index if is_prime?(index)
    end
    primes[-1]
  end

  def self.is_prime? n
    (2..Math.sqrt(n)).each do |i|
      return false if n % i == 0
    end
    true
  end
end
