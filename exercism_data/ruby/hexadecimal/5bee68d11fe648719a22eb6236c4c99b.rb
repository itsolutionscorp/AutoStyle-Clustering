class Hexadecimal
  def initialize(hex)
    @hex = hex.reverse.chars.map { |d| to_int(d) }
  end

  def to_decimal
    return 0 if @hex.any? { |d| d < 0 }
    @hex.each_with_index.map { |d, i| d * 16 ** i }.reduce(:+)
  end

  def to_int(c)
    c =~ /[0-9]/ ? c.to_i : c =~ /[a-f]/ ? c.ord - 87 : -1
  end
end
