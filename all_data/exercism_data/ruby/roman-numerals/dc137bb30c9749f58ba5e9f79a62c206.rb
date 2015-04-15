class Integer

  def to_roman
    roman = ""
    roman.insert(0, ones(digits[0])) if digits[0]
    roman.insert(0, tens(digits[1])) if digits[1]
    roman.insert(0, hundreds(digits[2])) if digits[2]
    roman.insert(0, thousands(digits[3])) if digits[3]
    roman
  end

  def digits
    self.to_s.chars.map(&:to_i).reverse
  end

  def ones(digit)
    {
      0 => '',
      1 => 'I',
      2 => 'II',
      3 => 'III',
      4 => 'IV',
      5 => 'V',
      6 => 'VI',
      7 => 'VII',
      8 => 'VIII',
      9 => 'IX',
    }.fetch digit
  end

  def tens(digit)
    {
      0 => '',
      1 => 'X',
      2 => 'XX',
      3 => 'XXX',
      4 => 'XL',
      5 => 'L',
      6 => 'LX',
      7 => 'LXX',
      8 => 'LXXX',
      9 => 'XC',
    }.fetch digit
  end

  def hundreds(digit)
    {
      0 => '',
      1 => 'C',
      2 => 'CC',
      3 => 'CCC',
      4 => 'CD',
      5 => 'D',
      6 => 'DC',
      7 => 'DCC',
      8 => 'DCCC',
      9 => 'CM',
    }.fetch digit
  end

  def thousands(digit)
    {
      0 => '',
      1 => 'M',
      2 => 'MM',
      3 => 'MMM',
    }.fetch digit
  end

end
