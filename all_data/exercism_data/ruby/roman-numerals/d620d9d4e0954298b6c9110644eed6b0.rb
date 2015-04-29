require "pry"

class String
  alias :roman_to_decimal_position_chars :gsub
end

class Fixnum
	DIGIT_TO_ROMAN = {1 => "I", 2 => "II", 3 => "III", 4 => "IV", 5 => "V", 6 => "VI", 7 => "VII", 8 => "VIII", 9 => "IX"}

  MAP_I = ["I","X","C","M"]
  MAP_V = ["V", "L", "D"]
  MAP_IVX = { "I" => MAP_I, "V" => MAP_V, "X" => MAP_I[1..3] }

	def to_roman
    map_each_digit_to_roman(to_s.reverse).reverse.join    
	end

  private

  def map_each_digit_to_roman(number_str)
    digit_position = -1

    number_str.each_char.map{|digit|  
      #convert each digit (0..9) to its roman_numeral (I,II,...,IX)
      #THEN
      #convert each roman_numeral to match its digit's decimal position
      digit_position += 1
      
      if digit > "0" 
        DIGIT_TO_ROMAN[digit.to_i].roman_to_decimal_position_chars(/[IVX]/){|ivx| MAP_IVX[ivx][digit_position]}
      else
        ""
      end
    }
  end

end
