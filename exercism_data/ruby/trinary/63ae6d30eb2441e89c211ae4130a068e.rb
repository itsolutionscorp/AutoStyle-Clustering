class Trinary
  def initialize(trinary_string)
    @digits = trinary_string.reverse.chars.map { |c| c.to_i }
  end

  def to_decimal
    result = 0
    @digits.each_with_index do |d, i|
      result += d * 3**i
    end
    result
  end
end
