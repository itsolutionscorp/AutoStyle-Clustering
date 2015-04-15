class Fixnum 

  def romans 
    {
      1 => 'I',
      2 => 'II',
      3 => 'III',
      4 => 'IV',
      5 => 'V',
      6 => 'VI',
      7 => 'VII',
      8 => 'VIII',
      9 => 'IX',
      10 => 'X',
      20 => 'XX',
      30 => 'XXX',
      40 => 'XL',
      50 => 'L',
      60 => 'LX',
      70 => 'LXX',
      80 => 'LXXX',
      90 => 'XC',
      100 => 'C',
      200 => 'CC',
      300 => 'CCC',
      400 => 'CD',
      500 => 'D',
      900 => 'CM',
      1000 => 'M',
      2000 => 'MM',
      3000 => 'MMM',
    }
  end


  def to_roman
    str = self.to_s
    reversed_nums = str.split('').reverse
    to_re = ""
    iter = 1
    nums = romans
    reversed_nums.each do |num|
      to_re = "#{nums[num.to_i * iter]}#{to_re}"
      iter *= 10
    end
    to_re
  end
end
