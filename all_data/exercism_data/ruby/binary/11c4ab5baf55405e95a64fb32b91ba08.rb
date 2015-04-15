class Binary
  def initialize(binary_string)
    @binary_digits = extract_binary_digits(binary_string)
  end

  def to_decimal
    binary_powers.inject(&:+)
  end

  private

  def binary_powers
    binary_digits.reverse.map.with_index do |binary_digit, index|
      binary_digit * 2 ** index
    end
  end

  attr_reader :binary_digits

  def extract_binary_digits(binary_string)
    binary_string.chars.map(&:to_i)
  end
end
