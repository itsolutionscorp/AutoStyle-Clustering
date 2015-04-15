class Fixnum

  ROMAN_NUMERALS_MAP = {
    1000 => 'M' ,
    900  => 'CM',
    500  => 'D' ,
    400  => 'CD',
    100  => 'C' ,
    90   => 'XC',
    50   => 'L' ,
    40   => 'XL',
    10   => 'X' ,
    9    => 'IX',
    5    => 'V' ,
    4    => 'IV',
    1    => 'I'
  }

  def to_roman(n=self)
    return '' if n < 1
    tuple = ROMAN_NUMERALS_MAP.detect { |k,v| n >= k }
    tuple.last + to_roman(n - tuple.first)
  end

end
