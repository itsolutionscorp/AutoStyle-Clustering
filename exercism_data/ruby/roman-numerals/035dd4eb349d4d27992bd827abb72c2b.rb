NUMERALS = {
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

class Fixnum
  def to_roman
    numeral = ""
    backwards_number = self.to_s.chars.reverse.map(&:to_i)
    backwards_number.each_with_index do |digit, i|
      place_value = i != 0 ? (10*i) : 1
      place_number = (digit * place_value)
      case digit
      when 1
        numeral += NUMERALS[place_number]
      when 2
        numeral += NUMERALS[(digit - 1) * place_value] * 2
      when 3
        numeral += NUMERALS[(digit - 2) * place_value] * 3
      when 4
        numeral += NUMERALS[place_number]
      when 5
        numeral += NUMERALS[place_number]
      when 6
        numeral += NUMERALS[(digit - 5) * place_value] + NUMERALS[(digit - 1) * place_value]
      when 7
        numeral += NUMERALS[(digit - 6) * place_value] * 2 + NUMERALS[(digit - 2) * place_value]
      when 8
        numeral += NUMERALS[(digit - 7) * place_value] * 3 + NUMERALS[(digit - 3) * place_value] 
      when 9
        numeral += NUMERALS[place_number]
      else
      end
    end
    return numeral.reverse
  end
end
