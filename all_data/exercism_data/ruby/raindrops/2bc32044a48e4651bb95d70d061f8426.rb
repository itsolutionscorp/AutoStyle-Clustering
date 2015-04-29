require 'prime'
class Raindrops
  def self.convert(num)
    number_array = num.prime_division.flatten.uniq.sort
    output_string = ""
    if number_array.include?(3)
      output_string += "Pling"
    end
    if number_array.include?(5)
      output_string += "Plang"
    end
    if number_array.include?(7)
      output_string += "Plong"
    end

    if output_string.empty?
      return num.to_s
    else
      return output_string
    end
  end
end
