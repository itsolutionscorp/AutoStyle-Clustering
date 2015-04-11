class Raindrops

    PRIME_CONVERSIONS = {
        3 => "Pling",
        5 => "Plang",
        7 => "Plong"
    }

    def self.convert num
        rain_string = ""
        PRIME_CONVERSIONS.each do |factor, conversion|
            rain_string << conversion if num % factor == 0
        end
        rain_string.empty? ? num.to_s : rain_string
    end

end
