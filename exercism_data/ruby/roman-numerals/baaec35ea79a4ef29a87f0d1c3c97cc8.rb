class Fixnum
  LOOKUP = {
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
    1    => "I",
    0    => ""
  }

  def to_roman
    LOOKUP.each do |arabic, roman|
      if self == arabic
        return roman
      elsif self > arabic
        return roman + (self - arabic).to_roman
      end
    end
  end

end
