class Raindrops
    def self.convert(num)
        out = ""
        if num % 3 == 0
            out += "Pling"
        end
        if num % 5 == 0
            out += "Plang"
        end
        if num % 7 == 0
            out += "Plong"
        end
        if out == ""
            out = num.to_s
        end
        out
    end
end
