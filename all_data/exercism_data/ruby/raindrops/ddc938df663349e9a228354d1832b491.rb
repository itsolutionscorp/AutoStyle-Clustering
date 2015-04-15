def factorizor(aNumber)
    factors = []
    (1..aNumber).each do |x|
        (1..aNumber).each do |y|
            factors.push(y) if x * y == aNumber
        end
    end
    factors.uniq
end

def keep_primes(anArray)
    prime_factors = []
    anArray.each do |x|
        prime_factors.push(x) if factorizor(x).length == 2
    end
    prime_factors
end

class Raindrops

    def self.convert(aNumber)
        factors = factorizor(aNumber)
        prime_factors = keep_primes(factors)
        returnString = ''
        returnString += "Pling" if prime_factors.include?(3)
        returnString += "Plang" if prime_factors.include?(5)
        returnString += "Plong" if prime_factors.include?(7)
        returnString += aNumber.to_s if returnString == ''
        returnString
    end
end
