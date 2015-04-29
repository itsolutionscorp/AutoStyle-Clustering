require 'Prime'

class Raindrops
  def self.convert(number)

    drop_speak = []
    has_primes = 0 #keeps track of whether our number has prime factors
    factors = Prime.prime_division(number) #finds prime factors and puts them into an array of 2-element arrays

    if factors.flatten.include?(3) #we flatten so the array of arrays becomes just 1 array that .include? can search
      drop_speak << "Pling"
      has_primes += 1
    end

    if factors.flatten.include?(5)
      drop_speak << "Plang"
      has_primes += 1
    end

    if factors.flatten.include?(7)
      drop_speak << "Plong"
      has_primes += 1
    end

    if has_primes == 0
      number.to_s
    else
      drop_speak.join
    end

  end
end
