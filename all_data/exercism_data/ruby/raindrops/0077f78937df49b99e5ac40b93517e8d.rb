class Raindrops
    PRIME_TO_RAINDROP = {
        3 => "Pling",
        5 => "Plang",
        7 => "Plong"
    }

    def self.convert(number)
        result = ""
        PRIME_TO_RAINDROP.each {|prime,raindrop| result += raindrop if number % prime == 0 }
        result = number.to_s if result.empty?
        return result
    end
end
