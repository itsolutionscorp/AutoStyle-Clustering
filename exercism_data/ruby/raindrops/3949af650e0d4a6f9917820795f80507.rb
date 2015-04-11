require 'prime'

class Raindrops
    def self.convert(number)
    	raindrops = {3 => "Pling", 5 => "Plang", 7 => "Plong"}
        keys_raindrops = Prime.prime_division(number).flatten & raindrops.keys
        (keys_raindrops.empty?) ? number.to_s : keys_raindrops.reduce("") { |acc, key| acc << raindrops[key] }
    end
end
