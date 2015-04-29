module Raindrops
  def self.convert number
    result = ""

    if number % 3 == 0
      result += "Pling"
    end

    if number % 5 == 0
      result += "Plang"
    end

    if number % 7 == 0
      result += "Plong"
    end

    result == "" ? number.to_s : result
  end
end
