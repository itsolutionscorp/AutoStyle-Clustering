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

  def calculate_thousands
    thousands_digit = self/1000
    @number = @number - (self/1000)*1000
    thousands[thousands_digit] + calculate_hundreds
  end

  def calculate_hundreds
    hundreds_digit = @number/100
    if hundreds_digit != 0
      @number =  @number - (self/100)*100
    end
    hundreds[hundreds_digit] + calculate_tens
  end

  def calculate_tens
    tens_digit = @number/10
    if tens_digit != 0
      @number = @number - (@number/10)*10
    end
    tens[tens_digit] + calculate_ones
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