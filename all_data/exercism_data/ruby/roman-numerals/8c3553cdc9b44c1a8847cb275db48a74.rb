class Fixnum
  def to_roman
    n = self
    roman = ''
    ROMAN_VALUES.each_with_index do |v, i|
      while n >= v
        n -= v
        roman += ROMAN_NUMERALS[i]
      end
    end
    roman
  end

  private
  ROMAN_VALUES = [ 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 ]
  ROMAN_NUMERALS = [ 'M', 'CM', 'D', 'CD', 'C', 'XC', 'L', 'XL', 'X', 'IX', 'V', 'IV', 'I' ]
end
