require 'prime'

class Raindrops

  def self.convert(number)

    response = ""
    prime_factorization = Prime.prime_division(number)

    prime_factorization.each do |tuple|
      tuple.each do |num|
        
        if num == 3
          response.concat("Pling")
        elsif num == 5
          response.concat("Plang")
        elsif num == 7
          response.concat("Plong")
        end
          
      end
    end

    if response.empty?
      response.concat(number.to_s)
    end
      
    return response
    
  end
end
