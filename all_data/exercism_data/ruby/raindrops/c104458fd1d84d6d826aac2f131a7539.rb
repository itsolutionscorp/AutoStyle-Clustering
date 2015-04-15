require 'prime'

class Raindrops
    def self.convert(number)
        sentence = ""
        prime_factors = Prime.prime_division(number).flatten
        sentence << "Pling" if prime_factors.include? 3
        sentence << "Plang" if prime_factors.include? 5
        sentence << "Plong" if prime_factors.include? 7
        sentence = number   if sentence == ""
        return sentence.to_s
    end
end
