class Integer

  def to_roman
    roman    = ''
    numerals = ['IVX', 'XLC', 'CDM', 'MMM']
    digits   = self.to_s.chars.map(&:to_i).reverse
    digits.each_with_index { |digit, i| roman.insert 0, match(digit, numerals[i]) }
    roman
  end

  def match(digit, numerals)
    {
      0 => '',
      1 => numerals[0],
      2 => numerals[0] * 2,
      3 => numerals[0] * 3,
      4 => numerals[0..1],
      5 => numerals[1],
      6 => numerals[1] + numerals[0],
      7 => numerals[1] + numerals[0] * 2,
      8 => numerals[1] + numerals[0] * 3,
      9 => numerals[0] + numerals[2],
    }.fetch digit
  end

end
