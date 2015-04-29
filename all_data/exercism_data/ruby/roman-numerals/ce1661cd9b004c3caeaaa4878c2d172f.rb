class Fixnum
  def to_roman
    digits = %w(1000 900 500 400 100 90 50 40 10 9 5 4 1).map(&:to_i)
    numerals = %w(M CM D CD C XC L XL X IX V IV I)
    roman_numerals = digits.zip(numerals).to_h

    return unless numeral = roman_numerals.detect { |k,v| k <= self }
    numeral_value, numeral_character = numeral
    div, mod = self.divmod(numeral_value)
    "#{numeral_character * div}#{mod.to_roman}"
  end
end
