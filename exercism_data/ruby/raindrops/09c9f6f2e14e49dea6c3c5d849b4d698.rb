class Raindrops
    RESPONSE = {3 => "Pling", 5 => "Plang", 7 => "Plong"}
    def self.convert(num)
        RESPONSE.keys.map{ |prime|
            RESPONSE[prime] if num % prime == 0
        }.compact.inject(&:+) or num.to_s
    end
end
