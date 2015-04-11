class Binary
  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    return 0 unless valid?
    @binary.chars.reduce(0) { |sum, bit|
      sum *= 2
      sum + bit_value(bit)
    }
  end

  private

  def bit_value(bit)
    case bit
    when "0" then 0
    when "1" then 1
    else raise ArgumentError('Argument must be either "0" or "1"')
    end
  end

  def valid?
    @binary =~ /^[01]*$/
  end
end
