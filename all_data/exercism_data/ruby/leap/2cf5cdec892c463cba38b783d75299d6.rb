require "bigdecimal"

class Year < BigDecimal
  def leap?
    divisible_by? 4 and
      not divisible_by? 100 or
      divisible_by? 400
  end

  def divisible_by? divisor
    self % divisor == 0
  end
end
