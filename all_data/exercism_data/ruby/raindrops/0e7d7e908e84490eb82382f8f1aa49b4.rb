class Raindrops
    PRIME_TO_RAINDROP = {
        3 => "Pling",
        5 => "Plang",
        7 => "Plong"
    }

    def self.convert(number)
        result = PRIME_TO_RAINDROP.select {|prime| number % prime == 0}.values.join
        result.empty? ? number.to_s : result
    end
end
