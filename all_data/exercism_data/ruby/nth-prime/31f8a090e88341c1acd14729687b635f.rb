class Prime
  def self.prime?(n)
    ((2..(Math.sqrt(n)))).each do |i|
      return false if n % i == 0
    end
    true
  end

  def self.nth(num)
    fail ArgumentError, 'Argument must be greater than 0' unless num > 0
    primes = []
    i = 2
    while primes.length < num
      primes << i if is_prime?(i)
      i += 1
    end
    primes[num - 1]
  end
end
