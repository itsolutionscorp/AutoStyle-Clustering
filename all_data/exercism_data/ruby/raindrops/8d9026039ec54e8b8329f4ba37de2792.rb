class Raindrops
  def self.convert(input)
    factors = Prime.find_prime_factors(input)
    unless input_has_required_primes(factors)
      return input.to_s
    end

    output = ""

    output << 'Pling' if factors.include?(3)
    output << 'Plang' if factors.include?(5)
    output << 'Plong' if factors.include?(7)

    output
  end

  def self.input_has_required_primes(factors)
    factors.include?(3) ||
    factors.include?(5) ||
    factors.include?(7)
  end
end

class Prime
  def self.find_prime_factors(input)
    factors = []
    while input > 1
      prime = find_divisor(input)
      factors << prime
      input /= prime
    end
    factors
  end

  def self.find_divisor(i)
    list = primes_until(i)

    div = list.find { |p| i % p == 0 }
    div || 1
  end

  def self.primes_until(num)
    primes = []
    (num+1).times do |i|
      primes << i if is_prime(i) && i > 1
    end
    primes
  end

  def self.is_prime(i)
    i.downto(2).none? do |j|
      i % j == 0 && j != i
    end
  end

  def self.find_biggest_prime(num)
    return num if num.prime?
    return 1   if num == 1
    find_biggest_prime(num-1)
  end
end
