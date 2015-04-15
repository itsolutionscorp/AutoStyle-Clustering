class Fixnum

def to_roman
  roman_1000 + roman_100 + roman_10 + roman_1
end

private

def roman_1000
  count = self / 1000
  'M' * count
end

def roman_100
  count = (self % 1000) / 100
  convert(count, 'C', 'D', 'M')
end

def roman_10
  c = (self % 100) / 10
  convert(c, 'X', 'L', 'C')
end

def roman_1
  c = self % 10
  convert(c, 'I', 'V', 'X')
end


def convert(count, one_unit, five_unit, ten_unit)
  return one_unit  + ten_unit              if count == 9
  return five_unit + one_unit * (count-5)  if count  > 4 
  return one_unit  + five_unit             if count == 4   
  return one_unit  * count
end

end
