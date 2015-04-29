class Binary
  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    return 0 if @binary.match(/[a-zA-Z2-9]/)
    digits = @binary.reverse.split('')
    digits.each_with_index.inject(0) { |sum, (d, i)| sum += d.to_i * 2 ** i }
  end
end
