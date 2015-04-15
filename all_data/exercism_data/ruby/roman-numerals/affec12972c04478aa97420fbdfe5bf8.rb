class Fixnum
  def to_roman
    one_to_ten = {1 => "I", 2 => "II", 3 => "III" , 4=> "IV",  5=> "V", 6=> "VI", 7=> "VII", 8=> "VIII" , 9=> "IX" }
    ten_to_hundred = {1 => "X", 2 => "XX", 3 => "XXX" , 4=> "XL",  5=> "L", 6=> "LX", 7=> "LXX", 8=> "LXXX" , 9=> "XC"}
    hundred_to_thousand = {1 => "C", 2 => "CC", 3 => "CCC" , 4=> "CD",  5=> "D", 6=> "DC", 7=> "DCC", 8=> "DCCC" , 9=> "CM"}
    thousands = {1 => "M", 2 => "MM", 3 => "MMM"}
    roman_num = ""
    digits = self.to_s.chars.map(&:to_i)
    case digits.length
    when 1
      roman_num << one_to_ten[digits[0]]
    when 2
      roman_num << ten_to_hundred[digits[0]]
      roman_num << one_to_ten[digits[1]] unless one_to_ten[digits[1]].nil?
    when 3
      roman_num << hundred_to_thousand[digits[0]]
      roman_num << ten_to_hundred[digits[1]] unless ten_to_hundred[digits[1]].nil?
      roman_num << one_to_ten[digits[2]] unless one_to_ten[digits[2]].nil?
    when 4
      roman_num << thousands[digits[0]]
      roman_num << hundred_to_thousand[digits[1]] unless hundred_to_thousand[digits[1]].nil?
      roman_num << ten_to_hundred[digits[2]] unless ten_to_hundred[digits[2]].nil?
      roman_num << one_to_ten[digits[3]] unless one_to_ten[digits[3]].nil?
    end
    roman_num
  end
end
