class Fixnum

def to_roman
  roman_numbers = {
    'I'=>1,
    'IV'=>4,
    'V'=>5,
    'IX'=>9,
    'X'=>10,
    'XL'=>40,
    'L'=>50,
    'XC'=>90,
    'C'=>100,
    'CD'=>400,
    'D'=>500,
    'CM'=>900,
    'M'=>1000
  }
  result = ''
  number = self
  roman_numbers.reverse_each do |k,v|
    while number >= v do
      result << k
      number -= v
    end
  end
result
end
end
