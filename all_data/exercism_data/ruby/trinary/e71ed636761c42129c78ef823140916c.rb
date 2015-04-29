class Trinary
  def initialize(number)
    @digits = number.chars
  end

  def to_decimal
    @digits.map.with_index do |digit, idx|
      digit.to_i * (3 ** (@digits.length - idx - 1))
    end.inject(:+)
  end
end
