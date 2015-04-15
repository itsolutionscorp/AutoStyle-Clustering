class Trinary
  def initialize(tri)
    @num = _parse tri
  end

  def to_decimal
    @num
  end

  def _parse(tri)
    tri.chars.reverse.each.with_index.map do |digit, position|
      position_value = position == 0 ? 1 : 3**position
      digit.to_i * position_value
    end.reduce(:+)
  end
end
