# binary.rb
class Binary
  attr_reader :binary

  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    return 0 if binary =~ /[^01]/
    enum = binary.each_char.reverse_each.with_index
    enum.reduce(0) do | sum, (bit, pow) |
      sum + (bit.to_i * 2**pow)
    end
  end
end
