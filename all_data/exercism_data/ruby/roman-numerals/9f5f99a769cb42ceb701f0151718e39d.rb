class Fixnum

  def to_roman

    thousands_digit = self/1000
    remainder = self - thousands_digit*1000

    hundreds_digit = remainder/100
    remainder = remainder - hundreds_digit*100
    
    tens_digit = remainder/10
    remainder = remainder - tens_digit*10

    ones_digit = remainder


    ones = make_roman_digit_encoding("I", "V", "X")
    tens = make_roman_digit_encoding("X", "L", "C")
    hundreds = make_roman_digit_encoding("C", "D", "M")
    thousands = make_roman_digit_encoding("M", "?", "?")

    return thousands[thousands_digit] + hundreds[hundreds_digit] + 
           tens[tens_digit] + ones[ones_digit]
  end

  private

  def make_roman_digit_encoding(unit, half, whole)
    value = unit
    encoding_map = {}
    10.times do |n|
      if n == 0 || n == 5
        value = value.gsub(unit, "") 
      elsif n == 4
        value = unit + half
      elsif n == 9 
        value = unit + whole
      else 
        value += unit
      end
      encoding_map[n] = value
    end
    encoding_map
  end

end
