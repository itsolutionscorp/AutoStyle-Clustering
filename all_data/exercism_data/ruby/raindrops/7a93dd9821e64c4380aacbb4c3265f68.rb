require 'prime'

class Raindrops

  def self.convert drops

    factors = self.factor_array_from(drops)

    output_string = ""

    if factors.include?(3) 
      output_string += "Pling"
    end

    if factors.include?(5)
      output_string += "Plang"
    end

    if factors.include?(7)
      output_string += "Plong"
    end

    if output_string != "" 
      output_string
    else
      drops.to_s
    end

  end

  def self.factor_array_from num
    Prime.each do |prime|
      if prime > num
        return []
      elsif (num % prime) == 0
        return [prime].concat(factor_array_from(num / prime))
      end
    end
  end

end
