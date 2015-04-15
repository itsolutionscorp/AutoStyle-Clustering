class Integer
  def to_roman()
    one_digits      = ['',   'I',  'II',  'III', 'IV',
                       'V', 'VI', 'VII', 'VIII', 'IX']
    ten_digits      = ['',   'X',  'XX',  'XXX', 'XL',
                       'L', 'LX', 'LXX', 'LXXX', 'XC']
    hundred_digits  = ['',   'C',  'CC',  'CCC', 'CD',
                       'D', 'DC', 'DCC', 'DCCC', 'CM']
    thousand_digits = ['',   'M',  'MM',  'MMM']

    roman  = thousand_digits[self/1000]
    roman += hundred_digits[(self%1000)/100]
    roman += ten_digits[(self%100)/10]
    roman += one_digits[self%10]
  end
end
