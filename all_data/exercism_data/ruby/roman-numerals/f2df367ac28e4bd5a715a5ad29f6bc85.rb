require "pry"

module RomanNumerals

	def to_roman
    to_s.reverse.decimal_str_to_roman.reverse.join    
	end

end

module RomanNumeralStrings
  #mapping to
  #convert each decimal digit to roman numerals, I thru IX
  SINGLE_DIGIT_TO_ROMAN = {0 => "", 1 => "I", 2 => "II", 3 => "III", 4 => "IV", 5 => "V", 6 => "VI", 7 => "VII", 8 => "VIII", 9 => "IX"}

  #mapping to
  #convert each ROMAN NUMERAL "I" thru "IX"
  #to ones, tens, hundreds, and thousands ROMAN representations
  MAP_I = ["I","X","C","M"]
  MAP_V = ["V", "L", "D"]
  MAP_IVX = { "I" => MAP_I, "V" => MAP_V, "X" => MAP_I[1..3] }


  def decimal_str_to_roman
    each_char.map.with_index{|digit, index|            
        SINGLE_DIGIT_TO_ROMAN[digit.to_i].to_upper_roman_chars(/[IVX]/){|ivx| MAP_IVX[ivx][index]}
    }
  end
end

class String
  include RomanNumeralStrings
  alias :to_upper_roman_chars :gsub
end

class Integer
  include RomanNumerals
end
