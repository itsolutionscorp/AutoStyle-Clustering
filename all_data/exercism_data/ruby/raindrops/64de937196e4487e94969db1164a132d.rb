class Raindrops
    def self.convert(a)
        result = ""
        result += "Pling" if a.modulo(3) == 0
        result += "Plang" if a.modulo(5) == 0
        result += "Plong" if a.modulo(7) == 0
        result = a.to_s if result.empty?
        return result
    end
end
