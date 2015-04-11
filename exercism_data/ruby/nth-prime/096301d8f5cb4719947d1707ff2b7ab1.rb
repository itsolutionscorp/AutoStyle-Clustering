class Prime
  def self.nth(number)
    raise ArgumentError.new if number < 1
    primes.take(number).to_a.last
  end

  def self.mostly_uneven_integers
    Enumerator.new do |y|
      y.yield 2
      a = 3
      loop do
        y.yield(a)
        a += 2
      end
    end
  end

  def self.primes
    mostly_uneven_integers.lazy.select {|p| is_prime?(p)}
  end

  def self.is_prime?(number)
    if number <= 3
      return number >= 2 
    elsif number % 2 == 0 || number % 3 == 0 
      return false
    end

    (5..Math.sqrt(number)+1).step(6).none? {|n| number % n == 0 || number % (n + 2) == 0}
  end
end
