class Binary
  attr_reader :to_decimal

  def initialize(binary_string)
    @to_decimal = 0
    binary_string.to_i.to_s.reverse.split('')
                 .each_with_index { |x, i| @to_decimal += x.to_i*2**i }
  end

end
