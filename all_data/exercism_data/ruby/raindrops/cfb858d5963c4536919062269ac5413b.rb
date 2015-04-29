class Raindrops
    def self.convert(num)
        response = ""
        factor = false
        if num % 3 == 0
            response += "Pling"
            factor = true
        end
        if num % 5 == 0
            response += "Plang"
            factor = true
        end
        if num % 7 == 0
            response += "Plong"
            factor = true
        end
        
        unless factor
            response = num.to_s
        end
        return response
        
    end
end
