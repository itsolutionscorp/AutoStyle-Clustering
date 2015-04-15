class Fixnum
  ROMAN = [
    [nil, 'I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX'],
    [nil, 'X', 'XX', 'XXX', 'XL', 'L', 'LX', 'LXX', 'LXXX', 'XC'],
    [nil, 'C', 'CC', 'CCC', 'CD', 'D', 'DC', 'DCC', 'DCCC', 'CM'],
    [nil, 'M', 'MM', 'MMM']
  ]
  MAX_LIMIT = 3000

  def to_roman
    return nil if self > MAX_LIMIT || self <= 0
    
    roman = []
    self.to_s.split(//).reverse.each_with_index do |n, i|
      roman << ROMAN[i][n.to_i]
    end
    roman.reverse.join
  end
end
