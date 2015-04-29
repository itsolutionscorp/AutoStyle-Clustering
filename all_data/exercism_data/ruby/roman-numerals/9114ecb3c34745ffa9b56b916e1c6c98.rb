class Fixnum
  ROMAN_MAP = {
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
       1 => "I"
  }
  def to_roman(previous_result="")
    return previous_result if self == 0

    ROMAN_MAP.keys.each do |divisor|
      times, remainder = self.divmod(divisor)
      previous_result << ROMAN_MAP[divisor]*times

      return remainder.to_roman(previous_result) if times > 0
    end
  end
end
