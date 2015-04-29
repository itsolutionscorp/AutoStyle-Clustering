module PrimeFactors
  def self.for(n)
    (2..n).each_with_object([]) do |possible_factor, factors|
      if possible_factor <= n
        power = times_prime_is_a_factor(n, possible_factor)
        power.times { factors << possible_factor }
        n = n / possible_factor**power
      end
    end
  end

  def self.factor?(n, prime)
    n >= prime && n % prime == 0
  end

  def self.times_prime_is_a_factor(n, prime)
    times = 0
    loop do
        return times unless factor?(n, prime)
        times += 1
        n = n / prime
    end
  end
end
