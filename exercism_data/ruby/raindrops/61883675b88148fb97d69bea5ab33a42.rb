require 'prime'

class Raindrops
  
  def self.convert(num)
    
    factors = Prime.prime_division(num).flatten

    string = factors.map { |prime_number| 
      case prime_number 
      when 3
        "Pling" 
      when 5
        "Plang"
      when 7
        "Plong"
      else
        next
      end }.join

      string.empty? ? num.to_s : string
  end    

end
