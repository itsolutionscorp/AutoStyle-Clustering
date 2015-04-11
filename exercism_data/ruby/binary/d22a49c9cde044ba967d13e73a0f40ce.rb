class Binary
  attr_reader :binary

  def initialize n
    @binary = n.to_i
  end

  def to_decimal
    binary.to_s.chars.map { |x| x.to_i }.reverse_each.with_index
          .reduce(0) { |sum, (digit, index)| sum + digit * 2**index }
  end
end
