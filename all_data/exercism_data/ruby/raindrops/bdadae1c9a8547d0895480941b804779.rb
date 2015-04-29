class Raindrops
  POSSIBLE = {"Pling" => 3,
              "Plang" => 5,
              "Plong" => 7
             }

  def self.convert(number)
    result = POSSIBLE.map do |drop, value|
      drop if number % value == 0
    end.join

    result.empty? ? number.to_s : result
  end
end
