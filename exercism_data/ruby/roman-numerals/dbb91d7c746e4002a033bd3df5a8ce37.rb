class Integer
  ROMAN_NUMERAL_MAPPINGS = [
    [1000, 'M' ],
    [ 900, 'CM'], [500, 'D'], [400, 'CD'], [100, 'C'],  
    [  90, 'XC'], [ 50, 'L'], [ 40, 'XL'], [ 10, 'X'],
    [   9, 'IX'], [  5, 'V'], [  4, 'IV'], [  1, 'I']
  ]

  # Converts an instance to roman numerals appended to a given string.
  # If the instance can't be converted, it returns the original string.
  def convert_and_append_roman_numerals_to(str)

    ROMAN_NUMERAL_MAPPINGS.each do |(value,roman_numerals)|
      next if value > self
      return (self - value).convert_and_append_roman_numerals_to(str+roman_numerals)
    end

    # Since the the smallest "value" in the above loop is 1
    # this also acts as our backstop when self < 1
    return str
  end
  
  def to_roman
    convert_and_append_roman_numerals_to ''
  end
  
end
