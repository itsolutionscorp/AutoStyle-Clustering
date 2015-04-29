class Binary
  attr_reader :binary_string
  def initialize(binary_string)
    @binary_string = binary_string
  end

  def to_decimal
    place_values.inject(&:+)
  end

private

  def place_values
    binary_string.reverse.chars.map.with_index do |digit, power|
      digit.to_i * (2 ** power)
    end
  end
end
