class Hexadecimal

  BASE = 16
  VALUES = %w(0 1 2 3 4 5 6 7 8 9 a b c d e f)

  def initialize(hex)
    @hex = hex.downcase
  end

  def to_decimal
    return 0 if @hex =~ /[^0-9a-f]/

    @hex.reverse.each_char.with_index.inject(0) do |sum,(n,i)|
      sum + VALUES.index(n) * BASE**i
    end
  end

end
