module Raindrops
    def self.convert(number)
      hash = {"Pling" => 3, "Plang" => 5, "Plong" => 7}
      raindrops = hash.select {|key, value| number % value == 0}
      raindrops.empty? ? "#{number}" : raindrops.keys.join
    end
end
