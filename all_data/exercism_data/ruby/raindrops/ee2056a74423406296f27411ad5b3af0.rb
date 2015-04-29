require 'prime'

class Raindrops

  attr_reader :drops, :factors

  def self.convert drops
    new(drops).conversion
  end

  def initialize drops
    @drops = drops
    @factors = factor_array_from(drops)
  end

  def conversion

    found_conversion = false
    output_string = ""

    if factors.include?(3) 
      found_conversion = true
      output_string += "Pling"
    end

    if factors.include?(5)
      found_conversion = true
      output_string += "Plang"
    end

    if factors.include?(7)
      found_conversion = true
      output_string += "Plong"
    end

    if found_conversion
      output_string
    else
      drops.to_s
    end

  end

  def factor_array_from num
    Prime.each do |prime|
      if prime > num
        return []
      else
        if (num % prime) == 0
          return [prime].concat(factor_array_from(num / prime))
        end
      end
    end
  end

end
