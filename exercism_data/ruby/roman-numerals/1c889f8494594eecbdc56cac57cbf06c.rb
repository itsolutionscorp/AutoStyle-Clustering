class Integer

  CONVERSIONS = {
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
    arabic_to_roman(to_i)
  end

  private
    def arabic_to_roman(num)
      return '' if num == 0
      CONVERSIONS.each do |arabic_key, roman_key|
        return roman_key + arabic_to_roman(num - arabic_key) if num >= arabic_key
      end
    end
end
