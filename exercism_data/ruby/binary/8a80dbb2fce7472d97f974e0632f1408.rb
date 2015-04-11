class Binary
  def initialize bin
    @bin = bin.match(/[^01]/) ? '0' : bin
  end

  def to_decimal
    @bin.each_char.inject(0) { |sum, n| sum * 2 + n.to_i }
  end
end
