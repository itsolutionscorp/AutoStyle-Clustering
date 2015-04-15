class Binary
  def initialize(bin)
    @bin = bin
  end

  def to_decimal
    return 0 if @bin.match(/[^01]/)
    return @bin.reverse.chars.each_with_index.map { |c, i| 2**i * c.to_i } .reduce(:+)
  end
end
