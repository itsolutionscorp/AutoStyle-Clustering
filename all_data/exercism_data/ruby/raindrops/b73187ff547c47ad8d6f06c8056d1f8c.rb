module Raindrops
  RAINDROPS = [['Pling', 3], ['Plang', 5], ['Plong', 7]]

  def self.convert n
    r = RAINDROPS.each_with_object '' do |e, a|
      k, v = e
      a << k if prime_factors(n).include? v
    end
    r.empty? ? n.to_s : r
  end

  def self.prime_factors m
    y = (1..m).each_with_object [] do |n, primes|
      x = primes.each_with_object [] do |i, divisors|
        divisors << i if n % i == 0
      end
      primes << n if x.length < 2
    end
    y[1..-1].each_with_object [] do |i, factors|
      factors << i if m % i == 0
    end
  end
end
