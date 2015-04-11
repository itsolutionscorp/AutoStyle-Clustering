class Roman
  NUMBERS_TO_NUMERALS = {
      1000 => 'M',
      900 => 'CM',
      500 => 'D',
      400 => 'CD',
      100 => 'C',
      90 => 'XC',
      50 => 'L',
      40 => 'XL',
      10 => 'X',
      9 => 'IX',
      5 => 'V',
      4 => 'IV',
      1 => 'I'
  }
end

class Numeric
  def to_roman
    number_to_convert = self
    Roman::NUMBERS_TO_NUMERALS.each_pair.reduce("") do | result, pair |
      number = pair[0]
      numeral = pair[1]
      # 27 / 10 = 2 x "X" = "XX"
      # got the 10's, move on to the next number: 27 % 10 = 7
      number_of_numerals = number_to_convert / number
      number_to_convert = number_to_convert % number
      result + numeral * number_of_numerals
    end
  end
end
