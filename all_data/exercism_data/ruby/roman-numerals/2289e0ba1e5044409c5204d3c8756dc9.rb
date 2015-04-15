class Fixnum
  def to_roman
    Roman.new(self).to_roman
  end
end


class Roman

  def initialize(num)
    @num = num
  end

  def to_roman
    roman_i = ['', 'I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX']
    roman_x = ['', 'X', 'XX', 'XXX', 'XL', 'L', 'LX', 'LXX', 'LXXX', 'XC']
    roman_c = ['', 'C', 'CC', 'CCC', 'CD', 'D', 'DC', 'DCC', 'DCCC', 'CM']
    roman_m = ['', 'M', 'MM', 'MMM']

    ones = @num % 10
    tens = ((@num - ones) % 100)/10
    hundreds = ((@num-(tens+ones)) % 1000)/100
    thousands = ((@num-(hundreds+tens+ones)) % 10000)/1000

    roman_numeral =  roman_m[thousands] << roman_c[hundreds] << roman_x[tens] << roman_i[ones]
  end

end
