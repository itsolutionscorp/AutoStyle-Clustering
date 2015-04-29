class Binary
  def initialize(binary)
    b = binary.to_i
    @decimal = 0
    m = 1
    while b > 0
      @decimal += (b % 10) * m
      m <<= 1
      b /= 10
    end
  end

  def to_decimal
    @decimal
  end
end
