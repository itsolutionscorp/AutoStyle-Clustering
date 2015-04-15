# trinary.rb
class Trinary
  attr_reader :trinary

  def initialize(trinary)
    @trinary = trinary
  end

  def to_decimal
    return 0 if trinary =~ /[^012]/
    enum = trinary.each_char.reverse_each.with_index
    enum.reduce(0) do | sum, (bit, pow) |
      sum + (bit.to_i * 3**pow)
    end
  end
end
