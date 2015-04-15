class Trinary
  def initialize string
    @string = string
  end

  def to_decimal
    digits.each_with_index
          .map { |digit, position| digit * 3**position }
          .reduce(:+)
  end

  def digits
    @string.chars.map(&:to_i).reverse
  end
end
