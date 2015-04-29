class Fixnum
  def to_roman
    numeral_map = {
      1000 => "M",
      900 => "CM",
      500 => "D",
      400 => "CD",
      100 => "C",
      90 => "XC",
      50 => "L",
      40 => "XL",
      10 => "X",
      9 => "IX",
      5 => "V",
      4 => "IV",
      3 => "III",
      2 => "II",
      1 => "I"
    }

    num_left = self
    numer = ""
    numeral_map.each do |num, letter|
      numer += letter * (num_left / num)
      num_left = num_left % num
    end
    return numer
  end
end
