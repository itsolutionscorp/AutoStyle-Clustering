require 'prime'

class Raindrops

  @@sounds = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }

  def self.convert drops

    output_string = ""

    @@sounds.each do |num, sound|
      if self.factor_array_from(drops).include?(num) 
        output_string += sound
      end
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
