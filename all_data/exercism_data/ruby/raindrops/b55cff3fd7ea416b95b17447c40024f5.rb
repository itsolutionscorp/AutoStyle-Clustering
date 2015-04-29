module Raindrops
    def self.convert(number)
        mapping = {3 => "Pling",
                   5 => "Plang",
                   7 => "Plong"}

        words = mapping.reduce("") do |result, (divisor, word)|
            if number % divisor == 0
                result += word
            else
                result
            end
        end

        if words.empty?
            return number.to_s
        end
        return words
    end
end
