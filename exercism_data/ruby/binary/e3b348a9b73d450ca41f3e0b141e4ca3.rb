class Binary
  attr_reader :binary

  def initialize n
    @binary = n.match(/[^01]/) ? '0' : n
  end

  def to_decimal
    binary.chars
          .map(&:to_i)
          .reverse_each
          .with_index
          .reduce(0) { |sum, (digit, index)| sum + digit * 2**index }
  end
end
