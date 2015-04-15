class Raindrops

  
  def self.convert(number)
    @number = number

    string = ""

    if @number % 3 == 0
      string += "Pling"
    end

    if @number % 5 == 0
      string += "Plang"
    end

    if @number % 7 == 0
      string += "Plong"
    end

    return string if string.size > 0
    
    return @number.to_s 

  end
  
end
