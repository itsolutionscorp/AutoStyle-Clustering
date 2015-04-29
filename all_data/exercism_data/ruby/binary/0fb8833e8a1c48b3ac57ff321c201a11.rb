class Binary
  def initialize binary_number_as_string
    @binary_str = binary_number_as_string
  end

  def to_decimal
    return 0 unless @binary_str.match(/\A[10]+\z/)
    reversed = @binary_str.chars.reverse
    reversed.map.with_index { |value, power| value.to_i * 2 ** power }.reduce(:+)
  end
end
