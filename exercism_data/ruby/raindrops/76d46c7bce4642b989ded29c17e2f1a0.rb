require 'prime'

class Raindrops
  def self.convert(number)
    flattened_array = detect_numbers(number)
    if (flattened_array.include?(3) || flattened_array.include?(5) || flattened_array.include?(7))
      ret_string = ""
      flattened_array.each do |x|
        if (x == 3)
          ret_string += "Pling"
        elsif (x == 5)
          ret_string += "Plang"
        elsif (x == 7)
          ret_string += "Plong"
        end
      end
    else
        ret_string = flattened_array.map! {|k| k.to_s}.join("")
    end
    ret_string
  end

  def self.detect_numbers(number_array)
    flattened_array = number_array.prime_division.flatten
    if (flattened_array.include?(3) || flattened_array.include?(5) || flattened_array.include?(7))
      flattened_array
    else
      number_array.to_s.scan(/\d/)
    end
  end
end
