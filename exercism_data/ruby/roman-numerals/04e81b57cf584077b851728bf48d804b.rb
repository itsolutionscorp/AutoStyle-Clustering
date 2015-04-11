class Fixnum
   $hash = {
       1 => 'I',
       4 => 'IV',
       5 => 'V',
       9 => 'IX',
      10 => 'X',
      40 => 'XL',
      50 => 'L',
     90 => 'XC',
     100 => 'C',
     400 => 'CD',
     500 => 'D',
     900 => 'CM',
    1000 => 'M'
  }

  def to_roman (str = '')
    return str if self == 0
    a = $hash.select {|x,y| x <= self}
   	str << a.values.last
   	(self - a.keys.last).to_roman(str)
  end
end
