# Class to print appropriate output for numbers when they have 3, 5, 7 as prime factors
class Raindrops
  def self.convert(num)
    primes = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }
    result = primes.map { |factor, text| text if (num % factor).zero? }.join
    result == '' ? num.to_s : result
  end
end
