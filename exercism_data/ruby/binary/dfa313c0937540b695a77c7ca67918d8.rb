class Binary
  def initialize(val)
    @val = val
  end

  def to_decimal
    return 0 if @val.match(/\D/)
    @val.chars.reverse.each_with_index.map { |val, i| 2**i * val.to_i }.inject(&:+)
  end
end
