class Raindrops
  PRIMES = {
      3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong'
  }

  def self.convert(from)
    factors = PRIMES.keys.select { |prime| from % prime == 0 }
    factors << from if factors.empty?
    factors.collect { |n| PRIMES.fetch(n, n) }.join
  end
end
