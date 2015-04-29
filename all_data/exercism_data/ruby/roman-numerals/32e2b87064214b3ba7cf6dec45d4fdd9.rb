class Fixnum
  DIGIT_ORDERS = [['I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX'],
  ['X', 'XX', 'XXX', 'XL', 'L', 'LX', 'LXX', 'LXXX', 'XC'],
  ['C', 'CC', 'CCC', 'CD', 'D', 'DC', 'DCC', 'DCCC', 'CM'],
  ['M', 'MM', 'MMM']]
   
  def to_roman
    construct_roman(self).reverse.join
  end
  
  def construct_roman arabic_num, pos=0
    current = arabic_num%10
    pos == 3 ? [arabic_num==0 ? "" : DIGIT_ORDERS[pos][arabic_num-1]] : 
    [current==0 ? "" : DIGIT_ORDERS[pos][current-1]].concat(construct_roman(arabic_num/10, pos += 1))
  end
end    
