class Fixnum
  def to_roman
    num_hash = {
      1000 => "M", 900 => "CM", 500 => "D", 400 => "CD",
      100 => "C", 90 => "XC", 50 => "L", 40 => "XL",
      10 => "X", 9 => "IX", 5 => "V", 4 => "IV", 1 => "I"
    }

    number = self
    roman = ""
    while number > 0
      num_to_add = (num_hash.keys).detect { |v| number / v > 0 }
      roman += num_hash[num_to_add]
      number -= num_to_add
    end
    roman
  end
end
