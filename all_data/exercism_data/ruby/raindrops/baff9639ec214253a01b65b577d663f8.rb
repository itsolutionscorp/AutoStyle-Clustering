require 'prime'

class Raindrops
  class << self
    def convert(int)
      primes = Prime.prime_division(int).flatten
      plingify(primes, int)
    end

    private

    def plingify(primes, int)
      output = ''
      output += "Pling" if primes.include?(3)
      output += "Plang" if primes.include?(5)
      output += "Plong" if primes.include?(7)
      output += int.to_s if output == ''
      output
    end
  end
end
