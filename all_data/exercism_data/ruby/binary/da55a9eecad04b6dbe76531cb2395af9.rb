# binary.rb
class Binary
  attr_reader :binary, :bits

  def initialize(binary)
    @binary = binary
    @bits = binary.split(//)
  end

  def to_decimal
    return 0 if binary =~ /[^01]/
    bits.reverse_each.with_index.reduce(0) do | sum, (bit, pow) |
      sum + (bit.to_i * 2**pow)
    end
  end
end
