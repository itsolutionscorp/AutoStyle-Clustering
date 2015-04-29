class Raindrops
  def self.convert(number)
    converted = ""
    converted += "Pling" if number % 3 == 0
    converted += "Plang" if number % 5 == 0
    converted += "Plong" if number % 7 == 0
    return number.to_s if converted.empty?
    converted
  end    
end
