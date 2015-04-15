class Binary
  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    return 0 unless valid?
    @binary.chars.reduce(0) { |sum, bit|
      sum *= 2
      sum + bit.to_i
    }
  end

  private

  def valid?
    @binary =~ /^[01]*$/
  end
end
