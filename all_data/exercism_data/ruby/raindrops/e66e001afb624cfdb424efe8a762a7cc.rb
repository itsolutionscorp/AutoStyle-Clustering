require 'prime'

class Raindrops
  def self.convert(number)
    primes = Prime.prime_division(number).flatten
    if !primes.include?(3) and !primes.include?(5) and !primes.include?(7)
      number.to_s
    else
      word = ""
      word += "Pling" if primes.include?(3)
      word += "Plang" if primes.include?(5)
      word += "Plong" if primes.include?(7)
      word
    end
  end
end
