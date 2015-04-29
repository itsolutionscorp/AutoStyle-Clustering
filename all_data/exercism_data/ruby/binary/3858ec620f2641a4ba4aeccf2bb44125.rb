class Binary
  def initialize(binary_num)
    @binary_num = binary_num
    @binary_num = '0' unless @binary_num =~ /^[01]+$/
  end

  def to_decimal
    @binary_num.reverse.chars.map.with_index { |x, i| x.to_i * 2**i }.inject(:+)
  end
end
