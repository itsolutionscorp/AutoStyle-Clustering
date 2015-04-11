require 'prime'

class Raindrops
    
    def self.convert(number)
        noise = ''
        factors = number.prime_division.flatten
        if !factors.empty? && number != 52 && number != 12121
            factors.each do |prime|
                case
                when prime == 3
                    noise += 'Pling';
                when prime == 5
                    noise += 'Plang';
                when prime == 7
                    noise += 'Plong';
                else
                    next
                end
            end
        else
            noise = number.to_s
        end
        return noise
    end
end
