class Raindrops

  def self.convert(num)
    rain_output = ""
    
    rain_output << "Pling" if num % 3 == 0
    rain_output << "Plang" if num % 5 == 0
    rain_output << "Plong" if num % 7 == 0

    rain_output.empty? ? num.to_s : rain_output
  end

end
