class Hexadecimal

  attr_reader :hex, :decimal_values

  def initialize(hex)
    @hex = hex.downcase
    @decimal_values ||= decimal_values
  end

  def to_decimal
    check_illegal || compute_decimal
  end

  def check_illegal
    0 if hex.chars.any? {|char| char > 'f'}
  end

  def decimal_values
    Hash[('0'..'9').zip(0..9)].merge Hash[('a'..'f').zip(10..15)]
  end

  def compute_decimal
    result = 0
    hex.chars.reverse.each_with_index do |char, i|
      result += digit_value(char, i)
    end
    result
  end

  def digit_value(digit, index)
    decimal_values[digit] * 16**index
  end

end
