class Integer
  ROMAN_NUMERALS =
  [
    ['I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX'],
    ['X', 'XX', 'XXX', 'XL', 'L', 'LX', 'LXX', 'LXXX', 'XC'],
    ['C', 'CC', 'CCC', 'CD', 'D', 'DC', 'DCC', 'DCCC', 'CM'],
    ['M', 'MM', 'MMM']
  ]

  def to_roman
    roman_numeral_type = 
    self.to_s.chars.reverse.each_with_index.inject("") do |roman_numeral, pair|
      (pair[0] == "0" ? "" : ROMAN_NUMERALS[pair[1]][pair[0].to_i-1] )  + roman_numeral
    end
  end
end
