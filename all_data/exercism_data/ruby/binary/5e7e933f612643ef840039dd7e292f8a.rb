class Binary
  def initialize(binary_string)
    @bits = binary_string
  end

  def to_decimal
    bits.reverse.map.with_index { |bit, position| bit * 2**position }.inject(:+)
  end

  def bits
    @bits.chars.map { |x| Integer(x) }
  rescue
    [0]
  end
end
