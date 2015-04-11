class Fixnum
  
  NUMERALS = ['I', 'V', 'X', 'L', 'C', 'D', 'M']

  def to_roman
    if (self > 3000)
      self.to_s
    else
      self.to_s.split('').reverse.map
        .with_index(0) { |digit, length| digit_to_numeral(digit.to_i, length) }
        .reverse.reduce(:+)
    end
  end

  def digit_to_numeral(digit, digit_length)
    offset = digit_length*2
    # digits 4 and 9, do something special!
    if digit % 5 == 4  
      NUMERALS[offset] + NUMERALS[ digit >= 5 ? offset + 2 : offset + 1]
    # all else
    else
      (digit >= 5 ? NUMERALS[offset + 1] : '') +  NUMERALS[offset] * (digit % 5) 
    end
  end
end
