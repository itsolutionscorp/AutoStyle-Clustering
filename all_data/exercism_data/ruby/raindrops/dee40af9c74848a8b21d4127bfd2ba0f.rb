require 'prime'

class Raindrops
  FACTORS = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }
  
  class << self
  
    def convert number
      selected = rain_factors(number)
      return number.to_s if selected.empty?
      return selected.map{ |factor| FACTORS[factor] }.join
    end

  private 

    def prime_factors(number)
      Prime.instance.prime_division(number).map do |factor| 
        factor[0]
      end
    end
  
    def rain_factors(number)
      prime_factors(number).select do |factor| 
        FACTORS.keys.include?(factor)
      end
    end
    
  end
end
