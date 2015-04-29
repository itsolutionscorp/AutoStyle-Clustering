class Fixnum
  ROMAN_CONVERSION = {
    1000 =>  'M',
    900  => 'CM',
    500  =>  'D',
    400  => 'CD',
    100  =>  'C',
    90   => 'XC',
    50   =>  'L',
    40   => 'XL',
    10   =>  'X',
    9    => 'IX',
    5    =>  'V',
    4    => 'IV',
    1    =>  'I',
  }.freeze

  def to_roman
    input = self

    ROMAN_CONVERSION.each_with_object("") do |(value, roman_value), res|
      res   << roman_value * (input/value)
      input %= value
    end
  end
end
