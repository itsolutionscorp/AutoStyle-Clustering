class Trinary
  def initialize trinary
    @trinary = trinary
  end

  def to_decimal
    digits = @trinary.chars.map(&:to_i)

    digits.reverse.each_with_index.map do |digit, index|
      digit * 3 ** index
    end.reduce(:+)
  end
end
