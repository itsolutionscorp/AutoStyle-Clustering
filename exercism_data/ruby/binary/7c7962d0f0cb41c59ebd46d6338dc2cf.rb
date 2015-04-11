class Binary
  def initialize(binary)
    @binary = binary.split('').map(&:to_i)
  end

  def to_decimal
    return 0 if @binary.any? { |bin| bin != 0 && bin != 1 }
    reverse_bin = @binary.reverse
    reverse_bin.each_index.map { |i| reverse_bin[i] * 2 ** i }.reduce(:+)
  end
end
