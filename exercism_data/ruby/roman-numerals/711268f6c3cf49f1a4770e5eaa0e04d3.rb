class Fixnum

  ROMANS = {
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

  def to_roman
    result = []
    stack = ROMANS.to_a
    result = convert_to_roman self, result, stack
    result.join ""
  end

  def convert_to_roman(value, result, stack)

    val = stack[0]
    int = val[0]
    numeral = val[1]

    quotient = value / int
    remainder = value % int

    while quotient > 0
      result << numeral
      quotient = quotient - 1
    end

    stack.delete_at(0)

    if (remainder == 0)
      result
    else
      convert_to_roman remainder, result, stack
    end
  end
end
