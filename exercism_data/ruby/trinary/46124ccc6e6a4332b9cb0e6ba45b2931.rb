class Trinary
  attr_reader :trinary_string

  def initialize(trinary_string)
    @trinary_string = trinary_string
  end

  def to_decimal
    digits.reverse_each.with_index.reduce(0) do |sum, (digit, index)|
      sum + digit * 3**index
    end
  end

  private

  def digits
    trinary_string.chars.map(&:to_i)
  end
end
