class Fixnum
  ROMAN_SYMBOL_VALUES = {
    1     => 'I',
    4     => 'IV',
    5     => 'V',
    9     => 'IX',
    10    => 'X',
    40    => 'XL',
    50    => 'L',
    90    => 'XC',
    100   => 'C',
    400   => 'CD',
    500   => 'D',
    900   => 'CM',
    1_000 => 'M'
  }

  def to_roman
    romanize[0]
  end

  private

  def romanize(number= self, in_roman="")
    return in_roman, number if number == 0

    roman_values.reverse.each do |value|
      next if number < value
      in_roman += ROMAN_SYMBOL_VALUES.fetch value
      number -= value
      in_roman, number = romanize(number, in_roman)
    end
    return in_roman, number
  end

  def roman_values
    ROMAN_SYMBOL_VALUES.keys.sort
  end
end
