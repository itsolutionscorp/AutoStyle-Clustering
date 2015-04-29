class Trinary
  attr_reader :trinary_string

  def initialize(trinary_string)
    @trinary_string = trinary_string
  end

  def to_decimal
    digits.reduce { |sum, digit| sum * 3 + digit }
  end

  private

  def digits
    trinary_string.chars.map(&:to_i)
  end
end
