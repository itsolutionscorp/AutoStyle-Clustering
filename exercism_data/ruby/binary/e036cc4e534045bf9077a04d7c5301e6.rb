class Binary
  attr_reader :to_decimal

  def initialize(binary_string)
    s = binary_string.to_i.to_s.reverse.split('')
    @to_decimal = 0
    s.each_with_index { |x, i| @to_decimal += x.to_i*2**i }
  end

end
