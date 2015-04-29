class Binary

  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    return 0 if @binary.match(/[^0-1]/)
    @binary.each_char.reduce(0) { |sum, d| sum * 2 + d.to_i }
  end

end
