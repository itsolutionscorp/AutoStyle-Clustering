class Trinary
  def initialize value
    @trinary = value.match(/[012]+/) ? value.reverse : 0
    @length = value.length
  end

  def to_decimal
    [*0...@length].reverse.map { |i| @trinary[i].to_i * 3**i}.inject(:+)
  end
end
