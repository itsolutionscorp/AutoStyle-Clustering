class Binary
  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    return 0 unless binary?
    @binary.reverse.chars.each_with_index.inject(0) do |decimal, (digit, index)|
      decimal + digit.to_i * (2**index)
    end
  end

  private

  def binary?
    @binary =~ /^[10]+$/
  end
end
