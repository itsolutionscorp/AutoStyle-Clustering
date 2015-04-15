class Fixnum
  def to_roman
  	num = self.to_s.chars.reverse
  	num[0] = num[0].gsub(/#{[(ONES.keys)]}/, ONES)
  	num[1] = num[1].gsub(/#{[(TENS.keys)]}/, TENS) if num[1]
  	num[2] = num[2].gsub(/#{[(HUNDREDS.keys)]}/, HUNDREDS) if num[2]
  	num[3] = num[3].gsub(/#{[(THOUSANDS.keys)]}/, THOUSANDS) if num[3]
  	num.reverse.join
  end
end


ONES = { '0' => '',
	     '1' => 'I',
         '2' => 'II',
         '3' => 'III',
         '4' => 'IV',
         '5' => 'V',
         '6' => 'VI',
         '7' => 'VII',
         '8' => 'VIII',
         '9' => 'IX'
        }

TENS = { '0' => '',
	     '1' => 'X',
         '2' => 'XX',
         '3' => 'XXX',
         '4' => 'XL',
         '5' => 'L',
         '6' => 'LX',
         '7' => 'LXX',
         '8' => 'LXXX',
         '9' => 'XC'
        }

HUNDREDS = { '0' => '',
	         '1' => 'C',
             '2' => 'CC',
             '3' => 'CCC',
             '4' => 'CD',
             '5' => 'D',
             '6' => 'DC',
             '7' => 'DCC',
             '8' => 'DCCC',
             '9' => 'CM'
            }

THOUSANDS = { '1' => 'M',
              '2' => 'MM',
              '3' => 'MMM',
            }
