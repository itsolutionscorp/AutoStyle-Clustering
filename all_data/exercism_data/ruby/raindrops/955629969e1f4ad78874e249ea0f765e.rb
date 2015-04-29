module Raindrops
    def self.convert(number)
      string = ""
      hash = {"Pling" => 3, "Plang" => 5, "Plong" => 7}

      hash.each_pair {|key, value| string << key if number % value == 0}

      string.empty? ? "#{number}" : string
    end
end
