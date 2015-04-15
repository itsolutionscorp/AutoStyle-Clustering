class Binary
  def initialize(string)
    @string = string
  end

  def to_decimal
    return 0 if @string.match(/[^0-9]/)
    array = @string.split(//)
    array.reverse.map.with_index { |x, i| x.to_i * (2**i) }.inject(&:+)
  end
end
