NUMERALS = {
	ones: ['I', 'V', 'X'],
	tens: ['X', 'L', 'C'],
	hundreds: ['C', 'D', 'M'],
	thousands: ['M']
}

class Fixnum
  def to_roman
	  numeral = ""
    backwards_number = self.to_s.chars.reverse
    d_keys = NUMERALS.keys
    backwards_number.each_with_index do |num, i|
      if num.to_i == 9
    		numeral +=  NUMERALS[d_keys[i]][2] + NUMERALS[d_keys[i]][0]
    	elsif num.to_i > 5
    		numeral +=  NUMERALS[d_keys[i]][0] * (num.to_i - 5) + NUMERALS[d_keys[i]][1]
      elsif num.to_i > 4
    		numeral += NUMERALS[d_keys[i]][1]
    	elsif num.to_i > 3
    		numeral +=  NUMERALS[d_keys[i]][1] + NUMERALS[d_keys[i]][0]
      else
        numeral += NUMERALS[d_keys[i]][0] * num.to_i
      end
    end
    return numeral.reverse
  end
end
