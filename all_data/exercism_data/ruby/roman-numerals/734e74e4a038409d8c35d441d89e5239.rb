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
    ROMAN_CONVERSION.reduce(["", self]) do |(res, remainder), (arabic_value, roman_value)|
      (times, remainder) = remainder.divmod(arabic_value)
      [res + roman_value * times, remainder]
    end.first
  end
end
