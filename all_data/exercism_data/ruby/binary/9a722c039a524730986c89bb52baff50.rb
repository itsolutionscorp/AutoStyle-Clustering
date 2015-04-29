class Binary
  def initialize binary_string
    @binary_string = binary_string
  end

  def to_decimal
    @binary_string.to_i(2)
  end
end
