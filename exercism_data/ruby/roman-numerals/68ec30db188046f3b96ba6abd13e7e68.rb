class Integer
  ARABIC_TO_ROMAN = {  1 => "I",   4 => "IV",
                       5 => "V",   9 => "IX",
                      10 => "X",  40 => "XL",
                      50 => "L",  90 => "XC",
                     100 => "C", 400 => "CD",
                     500 => "D", 900 => "CM",
                    1000 => "M"
                    }

  def to_roman
    remaining_value= self
    ARABIC_TO_ROMAN.keys.sort.reverse.inject("") do |numerals,key|
      multiplier,remaining_value = remaining_value.divmod(key)
      numerals << ARABIC_TO_ROMAN[key] * multiplier
    end 
  end

end
