class Raindrops
  ### 3 pling, 5 plang, 7 plong
  def self.convert(number)
    result = ""
    result += "Pling" if(number % 3 == 0) 
    result += "Plang" if(number % 5 == 0) 
    result += "Plong" if(number % 7 == 0)
    result += number.to_s if result == "" 
    result
  end
end
