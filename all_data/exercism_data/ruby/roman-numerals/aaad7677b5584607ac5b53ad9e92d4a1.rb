# Roman numerals coverter

module Roman
  def to_roman
    ones_numerals = {
      '1' => 'I',
      '2' => 'II',
      '3' => 'III',
      '4' => 'IV',
      '5' => 'V',
      '6' => 'VI',
      '7' => 'VII',
      '8' => 'VIII',
      '9' => 'IX',
      '0' => ''
    }

    tens_numerals = {
      '1' => 'X',
      '2' => 'XX',
      '3' => 'XXX',
      '4' => 'XL',
      '5' => 'L',
      '6' => 'LX',
      '7' => 'LXX',
      '8' => 'LXX',
      '9' => 'XC',
      '0' => ''
    }

    hundreds_numerals = {
      '1' => 'C',
      '2' => 'CC',
      '3' => 'CCC',
      '4' => 'CD',
      '5' => 'D',
      '6' => 'DC',
      '7' => 'DCC',
      '8' => 'DCCC',
      '9' => 'CM',
      '0' => ''
    }

    thous_numerals = {
      '1' => 'M',
      '2' => 'MM',
      '3' => 'MMM'
    }

    number_string = self.to_s
    roman_numeral = ''

    if number_string.length >= 4
      roman_numeral << thous_numerals[number_string[-4]]
    end
    if number_string.length >= 3
      roman_numeral << hundreds_numerals[number_string[-3]]
    end
    if number_string.length >= 2
      roman_numeral << tens_numerals[number_string[-2]]
    end
    if number_string.length >= 1
      roman_numeral << ones_numerals[number_string[-1]]
    end

    roman_numeral

  end
end

class Integer
  include Roman
end
