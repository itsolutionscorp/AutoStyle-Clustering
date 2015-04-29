class Integer

  def numerals
    {
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

  def to_roman
    roman_numeral = ''
    n = self
    numerals.each do |arabic, roman|
      roman_numeral << roman*(n / arabic)
      n -= arabic*(n / arabic)
    end
    roman_numeral
  end

end
