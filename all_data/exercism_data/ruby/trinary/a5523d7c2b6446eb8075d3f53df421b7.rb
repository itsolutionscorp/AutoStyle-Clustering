class Trinary
  def initialize(digits)
    @digits = digits
  end

  def to_decimal
    @digits.reverse.chars.each_with_index.inject(0) { |decimal, (digit, index)|
      decimal + digit.to_i * 3**index
    }
  end
end
