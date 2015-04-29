class Binary

  def initialize(binary_string)
    @digits = binary_string.chars.map(&:to_i)
  end

  def to_decimal
    @digits.inject() do |total, digit|
      total * 2 + digit
    end
  end
end
