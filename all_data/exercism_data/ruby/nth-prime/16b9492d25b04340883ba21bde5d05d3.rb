class Prime
  def self.nth(number)
    raise ArgumentError.new if number < 1
    primes.take(number).to_a.last
  end

  def self.primes
    (2..Float::INFINITY).lazy.select {|p| is_prime?(p)}
  end

  def self.is_prime?(number)
    (2..Math.sqrt(number+1)).none? {|n| number % n == 0 }
  end
end
