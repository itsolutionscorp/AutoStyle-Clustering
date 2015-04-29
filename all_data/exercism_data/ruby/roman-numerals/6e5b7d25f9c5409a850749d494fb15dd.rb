require "pry"

module RomanNumerals

	def to_roman
    integer_to_string.reverse.convert_decimal_string_to_roman_string_digit_by_digit.reverse.join    
	end

  private

  def integer_to_string
    to_s
  end
end

module RomanNumeralStrings
  #mapping to
  #convert each decimal digit to roman numerals, I thru IX
  DIGIT_TO_ROMAN_I_TO_IX = {0 => "", 1 => "I", 2 => "II", 3 => "III", 4 => "IV", 5 => "V", 6 => "VI", 7 => "VII", 8 => "VIII", 9 => "IX"}

  #mapping to
  #convert each ROMAN NUMERAL "I" thru "IX"
  #to ones, tens, hundreds, and thousands ROMAN representations
  MAP_I = ["I","X","C","M"]
  MAP_V = ["V", "L", "D"]
  MAP_IVX = { "I" => MAP_I, "V" => MAP_V, "X" => MAP_I[1..3] }


  def convert_decimal_string_to_roman_string_digit_by_digit
    each_char.map.with_index{|digit, index|            
        DIGIT_TO_ROMAN_I_TO_IX[digit.to_i].roman_least_numerals_to_1s_10s_100s_and_1000s(/[IVX]/){|ivx| MAP_IVX[ivx][index]}
    }
  end
end

class String
  include RomanNumeralStrings
  alias :roman_least_numerals_to_1s_10s_100s_and_1000s :gsub
end

class Integer
  include RomanNumerals
end
