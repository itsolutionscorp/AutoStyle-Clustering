class Fixnum
  ROMAN_I = [['','I','II','III','IV','V','VI','VII','VIII','IX'],\
    ['','X','XX','XXX','XL','L','LX','LXX','LXXX','XC'],\
    ['','C','CC','CCC','CD','D','DC','DCC','DCCC','CM'],\
    ['','M','MM','MMM']]
  def to_roman
    array_n = to_s.split(//).map(&:to_i)
    result = String.new
    array_n.each_index{|i| result << ROMAN_I[(array_n.size-1)-i][array_n[i]]}
    result
  end
end
