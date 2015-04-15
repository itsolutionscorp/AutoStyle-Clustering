require 'prime'

class Raindrops

  def self.convert(drops)
    primes = Prime.prime_division(drops).flatten.uniq
    word = []
    if primes.include?(3)
      word << 'Pling'
    end
    if primes.include?(5)
      word << 'Plang'
    end
    if primes.include?(7)
      word << 'Plong'
    end
    if word.size > 0
      word.join
    else
      drops.to_s
    end
  end

end
