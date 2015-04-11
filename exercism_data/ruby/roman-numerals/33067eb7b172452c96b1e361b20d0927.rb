class Fixnum
  ROMAN_SYMBOL_VALUES = {
    1     => 'I',
    5     => 'V',
    10    => 'X',
    50    => 'L',
    100   => 'C',
    500   => 'D',
    1_000 => 'M'
  }

  def to_roman
    handle_subtractive_notation(convert_to_roman)
  end

  private

  def convert_to_roman
    return romanize[0]
  end

  def romanize(number= self, in_roman="")
    return in_roman, number if number == 0
    roman_values.reverse.each do |k|
      if number >= k
        in_roman += ROMAN_SYMBOL_VALUES.fetch k
        number -= k
        in_roman, number = romanize(number, in_roman)
      end
    end
    return in_roman, number
  end

  def roman_values
    ROMAN_SYMBOL_VALUES.keys.sort
  end

  def roman_symbols
    ROMAN_SYMBOL_VALUES.values
  end

  def get_greater(symbol)
    roman_symbols[roman_symbols.index(symbol) + 1]
  end

  def handle_subtractive_notation(roman_numeral)
    notation_for_ones! roman_numeral
    notation_for_tens! roman_numeral
    notation_for_hundreds! roman_numeral
    roman_numeral
  end

  def notation_for_ones!(roman_numeral)
    substitute roman_numeral, 'I', 'IV', 'IX'
  end

  def notation_for_tens!(roman_numeral)
    substitute roman_numeral, 'X', 'XL', 'XC'
  end

  def notation_for_hundreds!(roman_numeral)
    substitute roman_numeral, 'C', 'CD', 'CM'
  end

  def substitute(numeral, symbol, lower_substitute, upper_substitute)
    repetition = /#{symbol}{4}/
    numeral.gsub! /#{get_greater(symbol)}#{repetition}/, upper_substitute
    numeral.gsub! repetition, lower_substitute
  end
end
