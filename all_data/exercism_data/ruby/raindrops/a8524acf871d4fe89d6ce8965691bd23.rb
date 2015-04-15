class Raindrops
  def self.convert(input_number)
    response = ""
    
    if input_number % 3 == 0 
      response+="Pling"
    end
    
    if input_number%5 ==0
      response+="Plang"
    end
    
    if input_number%7 ==0
      response+="Plong"
    end

    if response == ""
      response+=input_number.to_s
    end

    return response

  end

end
