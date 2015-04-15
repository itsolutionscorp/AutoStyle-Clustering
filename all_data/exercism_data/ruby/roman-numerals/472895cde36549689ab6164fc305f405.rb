class Fixnum

  def to_roman

    roman_key = {0 => '', 1 => 'I', 2 => 'II', 3 => 'III',
                 4 => 'IV', 5 => 'V', 6 => 'VI',7 =>'VII',
                 8 => 'VIII', 9 => 'IX',10 => 'X', 20 => 'XX',
                 30 => 'XXX', 40 => 'XL', 50 => 'L', 60 => 'LX',
                 70 => 'LXX', 80 => 'LXXX', 90 => 'XC', 100 => 'C',
                 200 => 'CC', 300 => 'CCC', 400 => 'CD', 500 => 'D',
                 600 => 'DC', 700 => 'DCC', 800 => 'DCCC', 900 => 'CM',
                 1000 => 'M', 2000 => 'MM', 3000 => 'MMM'}

      thousands = self/1000*1000
      if thousands != 0
        hundreds = (self%1000)/100*100
      else
        hundreds = self/100*100
      end
      if hundreds != 0
        tens = (self%100)/10*10
      else
        tens = (self%1000)/10*10
      end
      ones = self%10
      roman_key[thousands] + roman_key[hundreds] + roman_key[tens] + roman_key[ones]

  end

end
