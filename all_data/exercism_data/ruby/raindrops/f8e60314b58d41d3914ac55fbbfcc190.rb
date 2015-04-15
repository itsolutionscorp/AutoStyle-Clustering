class Raindrops
  def self.convert(number)
    divisibleBy = 0
    divisibleBy += 0b001 if number % 3 == 0
    divisibleBy += 0b010 if number % 5 == 0
    divisibleBy += 0b100 if number % 7 == 0
    if mask == 0
      number.to_s
    else
      result = ""
      result += "Pling" if (mask & 0b001) == 0b001
      result += "Plang" if (mask & 0b010) == 0b010
      result += "Plong" if (mask & 0b100) == 0b100
      result
    end
  end
end
  
