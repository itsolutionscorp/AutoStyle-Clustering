require 'pry'

module RomanNumerals

  LITERALS = {
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
    num = self

    LITERALS.reduce '' do |roman, (value, literal)|
      multiplicity, num = num.divmod( value )

      roman += literal * multiplicity
    end
  end

end

Integer.send :include, RomanNumerals
