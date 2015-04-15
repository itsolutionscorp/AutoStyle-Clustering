class Binary
  def initialize(binary_string)
    @binary_string = binary_string
  end

  def to_decimal
    return 0 if @binary_string =~ /[^0,1]/
    digits = @binary_string.split('')
    values = digits.reverse.map.with_index { |d, i| d.to_i * (2**i) }
    values.inject(:+)
  end
end
