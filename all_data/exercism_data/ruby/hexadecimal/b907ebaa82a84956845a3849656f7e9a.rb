class Hexadecimal

  attr_reader :hex, :decimal_values

  def initialize(hex)
    @hex = hex.downcase
  end

  def to_decimal
    zero_if_illegal || compute_decimal
  end

  def zero_if_illegal
    0 if hex.chars.any? {|char| char > 'f'}
  end

  def decimal_values
    @decimal_values ||= Hash[('0'..'9').zip(0..9)].merge Hash[('a'..'f').zip(10..15)]
  end

  def compute_decimal
    hex.chars.reverse.collect.with_index {|char, i| digit_value(char, i)}.inject(:+)
  end

  def digit_value(digit, index)
    decimal_values[digit] * 16**index
  end

end
