class Fixnum
  
  NUMERALS = ['I', 'V', 'X', 'L', 'C', 'D', 'M']
  
  #unused but handy to look at
  NUMERICS = [ 1,   5,   10,  50,  100, 500, 1000]

  def to_roman
    if (self > 3000)
      self.to_s
    else
      self.to_s.split('').reverse
        .map.with_index(0) { |digit, i| digit_to_numeral(digit.to_i, i) }
        .reverse.reduce(:+)
    end
  end

  def digit_to_numeral(digit, num_zeros)
    result = ""
    offset = num_zeros*2
    #for 4 or 9, do that special thing
    if digit % 5 == 4
      result = NUMERALS[offset] 
      result +=  NUMERALS[ digit >= 5 ? offset + 2 : offset + 1]
    #for everything else
    else
      result = digit >= 5 ? NUMERALS[offset + 1] : ''
      result += NUMERALS[offset] * (digit % 5) 
    end
  end
end
