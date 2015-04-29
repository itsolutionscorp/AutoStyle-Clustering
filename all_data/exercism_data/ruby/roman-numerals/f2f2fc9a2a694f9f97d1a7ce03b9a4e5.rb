class Fixnum
  ROMAN = [
      ['', 'M', 'MM', 'MMM'],
      ['', 'C', 'CC', 'CCC', 'CD', 'D', 'DC', 'DCC', 'DCCC', 'CM'],
      ['', 'X', 'XX', 'XXX', 'XL', 'L', 'LX', 'LXX', 'LXXX', 'XC'],
      ['', 'I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX'],
  ]

  def to_roman
    str = '%04d' % self

    str.chars.map.with_index{|c , i| ROMAN[i][c.to_i]}.join
  end
end
