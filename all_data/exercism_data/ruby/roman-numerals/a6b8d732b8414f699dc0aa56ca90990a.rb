class Fixnum
  def to_roman
    roman_pairs = {"M"=>1000, "CM"=>900, "D"=>500, "CD"=>400, "C"=>100, "XC"=>90, "L"=>50, "XL"=>40, "X"=>10, "IX"=>9, "V"=>5, "IV"=>4, "I"=>1}

    roman = ""
    temp_number = self

    roman_pairs.each do | key, value |
      roman << key * (temp_number / value)
      temp_number = temp_number % value
    end

    roman
  end
end
