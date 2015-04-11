class Integer < Numeric
  def to_roman
    digit_to_rom = {'1'=>'I', '2'=>'II', '3'=>'III', '4'=>'IV', '5'=>'V', '6'=> 'VI', '7'=> 'VII', '8'=> 'VIII', '9'=> 'IX'}
    tens_to_rom ={'1'=>'X', '2'=>'XX', '3'=>'XXX', '4'=>'XL', '5'=>'L','6'=>'LX', '7'=>'LXX', '8'=>'LXXX', '9'=>'XC'}
    hun_to_rom = {'1'=>'C', '2'=>'CC', '3'=>'CCC', '4'=>'CD', '5'=>'D', '6'=> 'DC', '7'=> 'DCC', '8'=> 'DCCC', '9'=> 'CM'}
    thousand_to_rom = {'1' =>'M', '2'=>'MM', '3'=>'MMM'}
    my_s = self.to_i.to_s
    n=my_s.length-1
    roman = ''
    x = ''
    while n>=0
      m=my_s.length-1-n
      if my_s[n]!='0'
        case m
          when 3 then x=thousand_to_rom[my_s[n]]
          when 2 then x=hun_to_rom[my_s[n]]
          when 1 then x=tens_to_rom[my_s[n]]
          else
            x=digit_to_rom[my_s[n]]
        end
        roman=x+roman
      end
      n-=1
    end
    roman
  end
end
