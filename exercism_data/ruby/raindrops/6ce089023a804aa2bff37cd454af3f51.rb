require 'Prime'

class Raindrops
    def self.convert(number)
        raindrop_speak = ''
        primes = Prime.prime_division(number).flatten.uniq

        raindrop_speak << 'Pling' if primes.include? 3
        raindrop_speak << 'Plang' if primes.include? 5
        raindrop_speak << 'Plong' if primes.include? 7
        raindrop_speak << number.to_s if raindrop_speak.empty? 

        raindrop_speak
    end
end
