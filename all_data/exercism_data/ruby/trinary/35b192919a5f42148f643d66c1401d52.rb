class Trinary
  attr_reader :trinary

  def initialize n
    @trinary = n.match(/[^012]/) ? '0' : n
  end

  def to_decimal
    trinary.chars
           .map(&:to_i)
           .reverse_each
           .with_index
           .reduce(0) { |sum, (digit, index)| sum + digit * 3**index }
  end
end
