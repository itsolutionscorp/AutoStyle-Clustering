class Object
  def to_roman #(num)
    num_to_roman = self
    ret_val = ''
    roman_numerals = { 1000 => "M", 900 => "CM", 500 => "D", 400 => "CD", 100 => "C",
      90 => "XC", 50 => "L", 40 => "XL", 10 => "X", 9 => "IX", 5 => "V", 4 => "IV", 1 => "I" }
  
  	until num_to_roman == 0 do
      roman_numerals.each do |k, v|
        if num_to_roman 
          if (num_to_roman / k) > 0
            ret_val += roman_numerals[k]
            num_to_roman -= k
            redo if (num_to_roman / k) > 0
          end
        end
      end
  	end
  
    # return
    ret_val
  end
end
