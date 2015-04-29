class Binary
  def initialize(binary)
    @digits = convert(binary)
  end

  def to_decimal
    @digits.each_with_index.inject(0) do |sum, (digit, idx)|
      sum += digit * 2**idx
    end
  end

  private

  def convert(binary)
    if binary.to_i.to_s != binary
      return [0]
    else
      a = binary.chars.inject([]) { |a,v| a << v.to_i }
      a.reverse
    end
  end
end
