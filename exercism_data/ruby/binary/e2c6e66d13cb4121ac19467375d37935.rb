class Binary
  def initialize(binary_number)
    fail ArgumentError, 'Argument given not a string containing only a binary number', caller unless binary_number.is_a? String
    @binary_number = binary_number
    @decimal_number = to_decimal
  end

  def to_decimal
    unless @decimal_number.nil?
      return @decimal_number
    end
    @binary_number.to_i(2)
  end
end
