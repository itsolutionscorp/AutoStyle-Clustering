module RomanNumerable

  def to_roman
    "#{thousands}#{hundreds}#{tens}#{ones}"
  end

private

  def thousands
    "M" * digit_at(1000)
  end

  def hundreds
    roman_hundreds.to_roman digit_at(100)
  end

  def tens
    roman_tens.to_roman digit_at(10)
  end

  def ones
    roman_ones.to_roman digit_at(1)
  end

  def digit_at(magnitude)
    to_i / magnitude % 10
  end

  RomanDigit = Struct.new(:one, :five, :ten) do
    def to_roman(value)
      case value
        when 1..3 then one * value
        when 4 then one + five
        when 5..8 then five + one * (value - 5)
        when 9 then one + ten
      end
    end
  end

  roman_hundreds = RomanDigit.new("C", "D", "M")
  roman_tens     = RomanDigit.new("X", "L", "C")
  roman_ones     = RomanDigit.new("I", "V", "X")

end


class Integer
  include RomanNumerable

end
