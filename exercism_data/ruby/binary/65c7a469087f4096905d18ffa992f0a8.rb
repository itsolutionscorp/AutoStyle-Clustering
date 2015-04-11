class Binary
  def initialize(n)
    @n = n.match(/[^01]/) ? [0] : n.chars.map { |d| d.to_i }
  end

  def to_decimal
    @n.reverse.each_with_index.reduce(0) { |sum, (digit, i)| sum + digit * (2**i) }
  end
end
