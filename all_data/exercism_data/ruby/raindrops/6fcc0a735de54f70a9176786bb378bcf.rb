class Raindrops
  def self.convert(input)
    factors = Prime.find_prime_factors(input)

    output = ""

    output << 'Pling' if factors.include?(3)
    output << 'Plang' if factors.include?(5)
    output << 'Plong' if factors.include?(7)

    output == "" ? input.to_s : output
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
    list = primes_until(i+1)

    div = list.find { |p| i % p == 0 }
    div || 1
  end

  def self.primes_until(num)
    (2..num).select do |i|
      is_prime(i)
    end
  end

  def self.is_prime(n)
    return n >= 1 if n <=3
    return false if n % 2 == 0 || n % 3 == 0

    i = 5
    while i*i <= n
       return false if (n % i == 0 || n % (i + 2) == 0)
       i += 6
    end

    true
  end
end
