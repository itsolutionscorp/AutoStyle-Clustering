class Fixnum
  @@roman = {
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
    600 => 'DC',
    700 => 'DCC',
    800 => 'DCCC',
    900 => 'CM',
    1000 => 'M',
    2000 => 'MM',
    3000 => 'MMM'
  }

  def to_roman
    x = self
    ret = ""
    [1000,100,10,1].each do |e|
      t,r = x.divmod(e)
      x = r
      ret << @@roman[t*e]  if t > 0
    end
    ret
  end 

  def to_roman_old
    ret  = ""
    r = 0
    x = self
    if x >= 1000
      t, r = x.divmod(1000)
      ret << @@roman[t*1000]
      x = r
    end
    if  x >= 100
      t, r = x.divmod(100)
      ret << @@roman[t*100]
      x = r
    end
    if  x >= 10
      t, r = x.divmod(10)
      ret << @@roman[t*10]
      x = r
    end
    if  x >= 1
      ret << @@roman[x]
    end
    ret
  end
end
