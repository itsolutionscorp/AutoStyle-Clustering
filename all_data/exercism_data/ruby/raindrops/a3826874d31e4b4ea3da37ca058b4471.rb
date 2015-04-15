require 'prime'

class Raindrops
    def self.convert(number)
        msg = ''
        number.prime_division.flatten.each do |prime|
            case prime
                when 3
                    msg << 'Pling';
                when 5
                    msg << 'Plang';
                when 7
                    msg << 'Plong';
            end
        end
        msg.empty?? number.to_s : msg
    end
end
