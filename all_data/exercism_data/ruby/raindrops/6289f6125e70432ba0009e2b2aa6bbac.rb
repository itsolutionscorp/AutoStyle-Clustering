module Raindrops
  RAINDROP_SPEAK = [
    ['Pling', 3],
    ['Plang', 5],
    ['Plong', 7]
  ]

  def self.convert(n)
    p = prime_factors(n)
    r = RAINDROP_SPEAK.inject('') do |memo, pair|
      k, v = pair
      memo += k if p.include? v
      memo
    end
    r.empty? ? n.to_s : r
  end

  def self.prime_factors(m)
    (1..m).inject([]) do |primes, n|
      primes << n if primes.inject([]) do |divisors, i|
                                         divisors << i if n % i == 0
                                         divisors
                                       end.length < 2
      primes
    end[1..-1].inject([]) do |factors, i|
                            factors << i if m % i == 0
                            factors
                          end
  end
end
