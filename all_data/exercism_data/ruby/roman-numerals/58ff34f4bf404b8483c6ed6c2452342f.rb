class Fixnum
  ROMAN_MAPPINGS = {
    1000 => 'M',
    900  => 'CM',
    500  => 'D',
    400  => 'CD',
    100  => 'C',
    90   => 'XC',
    50   => 'L',
    40   => 'XL',
    10   => 'X',
    9    => 'IX',
    5    => 'V',
    4    => 'IV',
    1    => 'I'
  }

  def to_roman
    integer = self
    ROMAN_MAPPINGS.each_with_object('') do |(arabic, roman), string|
      while integer >= arabic
        string << roman
        integer -= arabic
      end
    end
  end

end
