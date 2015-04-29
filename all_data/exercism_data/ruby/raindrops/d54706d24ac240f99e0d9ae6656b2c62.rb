class Raindrops
    def self.convert(nb)
        factored_nb = factorize(nb)
        return nb.to_s if nb <= 2
        res = ""
        res = "Pling" if factored_nb.include? 3
        res += "Plang" if factored_nb.include? 5
        res += "Plong" if factored_nb.include? 7
        return nb.to_s if res == ""
        res
    end

    def self.factorize(nb)
        return [nb] if nb <= 3
        factors = []
        divisor = 2
        while nb > 1
            while nb % divisor == 0
                factors << divisor
                nb /= divisor
            end
            divisor == 2 ? divisor += 1 : divisor += 2 # Even numbers aren't prime
        end
        factors
    end
end
