class Raindrops
  def self.convert(number)
    if number % 3 == 0 or number % 5 == 0 or number % 7 == 0
      result = ""
      result += "Pling" if number % 3 == 0
      result += "Plang" if number % 5 == 0
      result += "Plong" if number % 7 == 0
      result
    else
      number.to_s
    end
  end
end
  
