# Class to print appropriate output for numbers when they have 3, 5, 7 as prime factors
class Raindrops
  def self.convert(n)
    primes = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }
    primes.select { |factor, txt| (n % factor).zero? }.values.reduce(:concat) || n.to_s
  end
end
