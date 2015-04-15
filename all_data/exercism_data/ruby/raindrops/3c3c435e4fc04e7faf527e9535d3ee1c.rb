class Raindrops

  def self.convert (number)
    translation = {3 => "Pling", 5 => "Plang", 7 => "Plong"} 
    result = ""

    translation.each do |key, value|
      result << value if number % key == 0
    end

    result << number.to_s if result  == ""

    result
  end 
end
