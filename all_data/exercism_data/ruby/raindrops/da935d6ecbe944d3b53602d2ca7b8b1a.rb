class Raindrops
  def self.convert(number)
    converter.(number)
  end

  def self.converter
    RaindropsConverter.new
  end
end

class RaindropsConverter
  def call(number)
    divisibleBy = divisibility(number)
    if divisibleBy == 0
      number.to_s
    else
      plingPlongResult(divisibleBy)
    end
  end

  private
  def divisibility(number) 
    divisibleBy = 0
    divisibleBy += 0b001 if number % 3 == 0
    divisibleBy += 0b010 if number % 5 == 0
    divisibleBy += 0b100 if number % 7 == 0
    divisibleBy
  end

  def plingPlongResult(divisibleBy)
    result = ""
    result += "Pling" if (divisibleBy & 0b001) == 0b001
    result += "Plang" if (divisibleBy & 0b010) == 0b010
    result += "Plong" if (divisibleBy & 0b100) == 0b100
    result
  end
end
