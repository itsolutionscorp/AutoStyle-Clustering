class Trinary
  def initialize trin
    @trin = trin =~ /^012/ ? '0' : trin
  end

  def to_decimal
    @trin.each_char.inject(0) { |sum, n| sum * 3 + n.to_i }
  end
end
