class Integer
  def to_roman
    num = self
    return "" if num <=0 || num >= 4000
    signs = ['IIII','V','XXXX','L','CCCC','D','MMMM']
    roman = signs.inject("") do |mem, sign|
      x = sign.size+1
      d = num % x
      num = (num - d)/x
      mem = sign[0,d] + mem
    end
    roman.gsub!(/DCCCC/,'CM');
    roman.gsub!(/CCCC/,'CD');
    roman.gsub!(/LXXXX/,'XC');
    roman.gsub!(/XXXX/,'XL');
    roman.gsub!(/VIIII/,'IX');
    roman.gsub!(/IIII/,'IV');
    roman
  end
end
