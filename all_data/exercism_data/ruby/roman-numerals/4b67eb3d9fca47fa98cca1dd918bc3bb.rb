require 'pry'

class Integer

  def to_roman

    roman_numerals = {1 => "I", 4 => "IV", 5 => "V", 9 => "IX", 10 => "X", 40 => "XL", 50 => "L", 90 => "XC", 100 => "C", 400 => "CD", 500 => "D", 900 => "CM", 1_000 => "M"}

    num = self
    string = ""
    temp = []

    while num > 0
      roman_numerals.each do |integer, roman_numeral|
        temp << integer if integer <= num
      end
      string += roman_numerals[temp.max].to_s
      num = num - temp.pop
      temp = []
    end

    string
  end

end
