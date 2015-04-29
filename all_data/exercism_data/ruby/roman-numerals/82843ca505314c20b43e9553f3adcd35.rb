class Integer < Numeric

  def to_roman
    @number = self
    if self/10 == 0
      calculate_ones
    elsif self/10 < 10
      calculate_tens
    elsif self/10 < 100
      calculate_hundreds
    elsif self/10 < 1000
      calculate_thousands
    end
  end

  def calculate(digit)

  end

  def calculate_thousands
    digit = @number/1000
    @number = @number - (@number/1000)*1000
    thousands[digit] + calculate_hundreds
  end

  def calculate_hundreds
    digit = @number/100
    @number =  @number - (@number/100)*100
    hundreds[digit] + calculate_tens
  end

  def calculate_tens
    digit = @number/10
    @number = @number - (@number/10)*10
    tens[digit] + calculate_ones
  end

  def calculate_ones
    ones[@number]
  end

  def ones
    {
      0 => "",
      1 => "I",
      2 => "II",
      3 => "III",
      4 => "IV",
      5 => "V",
      6 => "VI",
      7 => "VII",
      8 => "VIII",
      9 => "IX"
    }
  end

  def tens
    {
      0 => "",
      1 => "X",
      2 => "XX",
      3 => "XXX",
      4 => "XL",
      5 => "L",
      6 => "LX",
      7 => "LXX",
      8 => "LXXX",
      9 => "XC"
    }
  end

  def hundreds
    {
      0 => "",
      1 => "C",
      2 => "CC",
      3 => "CCC",
      4 => "CD",
      5 => "D",
      6 => "DC",
      7 => "DCC",
      8 => "DCCC",
      9 => "CM"
    }
  end

  def thousands
    {
      1 => "M",
      2 => "MM",
      3 => "MMM"
    }
  end

end
