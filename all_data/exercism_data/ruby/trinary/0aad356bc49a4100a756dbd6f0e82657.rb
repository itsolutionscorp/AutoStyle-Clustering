class Trinary

  def initialize str
    @digits = str
  end

  def to_decimal
    @digits.chars.inject(0) { |value, d| 3*value+d.to_i }
  end
end
