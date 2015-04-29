class Integer
  ROMAN_NUMERALS = %w(IVX XLC CDM M)
  ROMAN_PATTERNS = ['', '1', '11', '111', '15', '5', '51', '511', '5111', '10']

  def to_roman
    fail ArgumentError, "No roman numeral greater than M" if self >= 4000

    to_s.each_char.reverse_each.with_index.map do |char, power|
      ROMAN_PATTERNS[char.to_i].tr('150', ROMAN_NUMERALS[power])
    end.reverse.join
  end
end
