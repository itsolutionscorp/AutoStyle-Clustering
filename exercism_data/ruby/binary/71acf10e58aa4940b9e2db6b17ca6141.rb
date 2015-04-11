class Binary
  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    @binary.to_i(2)
  end
end
