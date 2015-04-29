require 'prime'

class Raindrops
  class << self
    def convert(integer)
      prime_factors = Prime.prime_division(integer).flatten
      rainspeak = ''
      if prime_factors.include?(3)
        rainspeak += 'Pling'
      end
      if prime_factors.include?(5)
        rainspeak += 'Plang'
      end  
      if prime_factors.include?(7)
        rainspeak += 'Plong'
      end
      if rainspeak.empty?
        integer.to_s
      else
        rainspeak
      end
    end

  end 

end
