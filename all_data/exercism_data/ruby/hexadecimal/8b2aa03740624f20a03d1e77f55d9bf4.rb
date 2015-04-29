# hexadecmimal.rb
class Hexadecimal
  attr_reader :hex

  def initialize(hex)
    @hex = hex
  end

  def to_decimal
    return 0 if hex =~ /[^0-9a-f]/
    enum = hex.downcase.each_char.reverse_each.with_index
    enum.reduce(0) do | sum, (bit, pow) |
      sum + (bit_to_i(bit) * 16**pow)
    end
  end

  def bit_to_i(bit)
    case bit
    when /[a-f]/ then bit.ord - 87
    else bit.to_i
    end
  end
end
