class Fixnum

  def to_roman
    roman_numeral = {
          1000 =>'M',
          900   =>'CM',
          500   => 'D',
          400   => 'CD',
          100   => 'C',
          90     => 'XC',
          50     => 'L',
          40     => 'XL',
          10     => 'X',
          9       => 'IX',
          5       => 'V',
          4       => 'IV',
          3       => 'III',
          2       => 'II',
          1       => 'I'
    }

    result = ''
    number = self

    roman_numeral.each do | card, roman|
      while number >= card
        result += roman
        number -= card
      end
    end
    result
  end
end
