require 'prime'

class Raindrops
  def self.convert(drops)
  	primes = Prime.prime_division(drops).map {|i| i[0]}
  	return drops.to_s unless (primes & [3, 5, 7]).any?
    "".tap do |i|
      (i << "Pling") if primes.include?(3)
      (i << "Plang") if primes.include?(5)
      (i << "Plong") if primes.include?(7)
    end
  end
end
