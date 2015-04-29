class Trinary
  attr_reader :to_decimal

  def initialize str
    @digits = str
    @to_decimal ||= convert
  end

  def convert
    @digits.chars.inject(0) { |value, d| 3*value+d.to_i }
  end
end
