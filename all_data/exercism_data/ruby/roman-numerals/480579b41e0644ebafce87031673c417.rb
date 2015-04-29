class Fixnum
  def to_roman
    roman_pairs = {"M"=>1000, "CM"=>900, "D"=>500, "CD"=>400, "C"=>100, "XC"=>90, "L"=>50, "XL"=>40, "X"=>10, "IX"=>9, "V"=>5, "IV"=>4, "I"=>1}
    roman_string = ""

    roman_pairs.reduce(self) do | temp_number, roman_pair |
      roman_string << roman_pair.first * (temp_number / roman_pair.last)
      temp_number % roman_pair.last
    end

    roman_string
  end
end
