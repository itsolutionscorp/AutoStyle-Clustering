class Fixnum
  def to_roman
    roman = Roman.new(self)
    roman.roman_equivalent
  end
end

  class Roman
  attr_accessor :number, :roman_equivalent

  ROMAN_EQUIVALENCES = {
    1 => 'I',
    4 => "IV",
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
    1000 => 'M'
  }

  def initialize(number)
    @number = number
    @roman = ''
    @roman_equivalent = romanize(number)
  end

  def romanize(number)
    largest_number_matched = ROMAN_EQUIVALENCES.keys.select { |key| number >= key }.max
    roman_value = ROMAN_EQUIVALENCES[largest_number_matched]
    @roman << roman_value
    remaining = number - largest_number_matched
    romanize(remaining) unless remaining <= 0
    @roman
  end

end
