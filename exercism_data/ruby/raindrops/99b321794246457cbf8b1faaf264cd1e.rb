require 'prime'

class Raindrops
    RAINDROPS = { "3" => "Pling", "5" => "Plang", "7" => "Plong"}

    def self.convert(integer)
        rain = find_factors(integer, RAINDROPS)
        argument_or_raindrops(rain, integer)
    end

    private

    def self.argument_or_raindrops(rain_factors, int)
        rain_factors.compact.empty? ? "#{int}" : rain_factors.join("")
    end

    def self.factor(number)
        Prime.prime_division(number).flatten
    end

    def self.find_factors(num, factors)
        factors.keys.map do |factor|
            factors.fetch(factor) if factor(num).include?(factor.to_i)
        end
    end
end
