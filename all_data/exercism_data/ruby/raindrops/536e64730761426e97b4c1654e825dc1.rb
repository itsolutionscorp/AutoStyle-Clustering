require 'prime'


class Raindrops

  

  def self.convert(number)
    prime_factors = number.prime_division.flatten

    p357 = false
    output = ""

    if prime_factors.include? 3
      output << "Pling"
      p357 = true
    end
    if prime_factors.include? 5
      output << "Plang"
      p357 = true
    end
    if prime_factors.include? 7
      output << "Plong"
      p357 = true
    end

    if p357 == false
      return number.to_s
      
    end

    output

  end

  

  
end
