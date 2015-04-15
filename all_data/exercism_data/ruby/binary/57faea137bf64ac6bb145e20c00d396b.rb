class Binary
  def initialize(b)
    @b = b.scan(/[^01]/).length > 0 ? '0' : b
  end

  def to_decimal
    d = 0
    @b.chars.reverse.each.with_index(0) do |c, i|
      d += c == '1' ? (2**i) : 0
    end; d
  end
end
