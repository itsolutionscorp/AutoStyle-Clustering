module RomanNumerable

  def to_roman
    "#{thousands}#{hundreds}#{tens}#{ones}"
  end

private

  def thousands
    "M" * (to_i / 1000 % 10)
  end

  def hundreds
    translate_digit to_i / 100 % 10, "C", "D", "M"
  end

  def tens
    translate_digit to_i / 10 % 10, "X", "L", "C"
  end

  def ones
    translate_digit to_i % 10, "I", "V", "X"
  end

  def translate_digit(digit, ones, fives, tens)
    case digit
      when 1..3 then ones * digit
      when 4 then ones + fives
      when 5..8 then fives + ones * (digit - 5)
      when 9 then ones + tens
    end
  end

end


class Integer
  include RomanNumerable

end
