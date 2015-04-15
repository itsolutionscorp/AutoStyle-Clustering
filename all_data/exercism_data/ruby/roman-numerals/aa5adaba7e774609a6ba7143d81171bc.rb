class Fixnum
@@numerals = {
  1000 =>  "M",
   900 => "CM",
   500 =>  "D",
   400 => "CD",
   100 =>  "C",
    90 => "XC",
    50 =>  "L",
    40 => "XL",
    10 =>  "X",
     9 => "IX",
     5 =>  "V",
     4 => "IV",
     1 =>  "I",
}

def to_roman
  num=self
  roman_numeral=""
  @@numerals.each do |value, numeral|
    num_of_numeral = num/value
    roman_numeral << numeral*num_of_numeral
    num=num-num_of_numeral*value
  end
  roman_numeral
end

end
