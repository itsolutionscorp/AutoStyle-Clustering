class Fixnum
  LOOKUP_TABLE = {
    1000 => "M",
    900  => "CM",
    500  => "D",
    400  => "CD",
    100  => "C",
    90   => "XC",
    50   => "L",
    40   => "XL",
    10   => "X",
    9    => "IX",
    5    => "V",
    4    => "IV",
    1    => "I"
    }

  def to_roman
    num_to_convert = self
    roman_num = []
    LOOKUP_TABLE.each do |num, roman|
      while num_to_convert >= num
        roman_num << roman
        num_to_convert -= num
      end
    end
    roman_num.join("")
  end
end
