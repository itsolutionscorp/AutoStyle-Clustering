class Binary
  attr_reader :value

  def initialize(value)
    @value = value
  end

  def to_decimal
    return 0 unless is_decimal?

    chars.each_with_index
      .map { |digit, index| digit.to_i * (2 ** index) }
      .inject(&:+)
  end

  private

  def chars
    value.reverse.each_char
  end

  def is_decimal?
    value.match(/^(0|1)+$/)
  end
end
