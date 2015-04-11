class Raindrops
  class << self

    def convert (number)
      return number.to_s if number % 3 != 0 && number % 5 != 0 && number % 7 != 0  
      
      num_str = ""
      
      if number % 3 == 0
        num_str << "Pling"  
      end

      if number % 5 == 0
        num_str << "Plang"  
      end

      if number % 7 == 0
        num_str << "Plong"  
      end

      return num_str
    end

  end
end
