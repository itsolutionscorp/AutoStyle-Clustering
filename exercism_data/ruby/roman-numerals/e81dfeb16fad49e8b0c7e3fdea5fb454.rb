class Fixnum
  ROMAN_NUMERALS = {1 => 'I', 
    4 => 'IV', 
    5 => 'V', 
    9 => 'IX',
    10 => 'X',
    40 => 'XL',
    50 => 'L',
    90 => 'XC',
    100 => 'C',
    400 => 'CD',
    500 => 'D',
    900 => 'CM',
    1000 => 'M'}

  def to_roman
    to_roman_inner(self, "")
  end

  private
  def to_roman_inner(value, string)
    if value==0
      return string
    else
      numeral = find_largest_roman_numeral(value)
      to_roman_inner(value-numeral[0], string+numeral[1])
    end
  end
  
  def find_largest_roman_numeral(value)
    ROMAN_NUMERALS.reject {|k,v| k > value }.max_by {|k,v| k}
  end
end
