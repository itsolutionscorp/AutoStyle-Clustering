require 'prime'

class Raindrops

  def self.convert(number)
    output = ""
    pf_hash = Prime.prime_division(number).to_h

    pf_hash.keys.each do |k| 

      if (k == 3) 
        output << "Pling"
      elsif (k == 5) 
        output << "Plang" 
      elsif (k == 7) 
        output << "Plong"     
      end

    end

    if (output.empty?)
      output = number.to_s
    end

    output

  end

end
