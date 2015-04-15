require 'Prime'
class Raindrops
  def self.convert(int)
    result = ''
    primes = int.prime_division.flatten
    result << 'Pling' if primes.include? 3
    result << 'Plang' if primes.include? 5
    result << 'Plong' if primes.include? 7
    result << int.to_s if result.empty?
    result
  end
end
