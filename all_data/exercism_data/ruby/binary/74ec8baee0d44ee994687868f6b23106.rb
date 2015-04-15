class Binary
  def initialize(binary_number)
    @binary_number = binary_number
  end

  def to_decimal
    return 0 unless /\A[-+]?\d+\z/ === @binary_number
    @binary_number.reverse.chars.each_with_index.inject { |sum, (digit, i)| sum + digit.to_i * 2 ** i }.reduce(:+)
  end
end
