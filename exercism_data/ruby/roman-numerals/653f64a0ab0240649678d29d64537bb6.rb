class Fixnum
  def to_roman
    roman_pairs = {"M"=>1000, "CM"=>900, "D"=>500, "CD"=>400, "C"=>100, "XC"=>90, "L"=>50, "XL"=>40, "X"=>10, "IX"=>9, "V"=>5, "IV"=>4, "I"=>1}

    output = roman_pairs.reduce([self,""]) do | (remainder, roman_string), (rpair_char, rpair_num) |
      [ remainder % rpair_num, roman_string << rpair_char * (remainder / rpair_num) ]
    end

    output.last
  end
end
