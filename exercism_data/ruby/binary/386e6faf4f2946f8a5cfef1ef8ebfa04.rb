class Binary

  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    valid? ? convert : 0
  end

  def convert
    @binary.chars.map(&:to_i).reverse.map.with_index { |d, i| d * 2 ** i }.inject(:+)
  end

  def valid?
    @binary =~ /^[0-1]/
  end

end
