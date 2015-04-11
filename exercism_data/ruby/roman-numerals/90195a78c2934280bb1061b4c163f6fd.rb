class Numeric
  def to_roman
    numerals = [
      ['', 'I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX'],
      ['',  'X', 'XX', 'XXX', 'XL', 'L', 'LX', 'LXX', 'LXXX', 'XC'],
      ['', 'C', 'CC', 'CCC', 'CD', 'D', 'DC', 'DCC', 'DCCC', 'CM'],
      ['', 'M', 'MM', 'MMM']
    ]

    self.to_s
        .chars
        .map { |char| char.to_i }
        .reverse
        .each_with_index.map { |val, index| numerals[index][val] }
        .reverse
        .join
  end
end
