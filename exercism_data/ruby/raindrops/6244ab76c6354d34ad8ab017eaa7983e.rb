require 'prime'

class Raindrops
    def self.convert(integer)
        divisors = integer.prime_division

        if divisors.empty?
            return integer.to_s
        else
            output = divisors.map do |factors|
                factors.map do |f|
                    case f
                    when 3
                        'Pling'
                    when 5
                        'Plang'
                    when 7
                        'Plong'
                    end
                end.join
            end.join

            if output.length > 0
                return output
            else
                return integer.to_s
            end
        end
    end
end
