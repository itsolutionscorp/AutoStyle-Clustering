class Trinary
  attr_accessor :trinary
  def initialize(trinary)
    @trinary = trinary.reverse.chars.map(&:to_i)
  end

  def to_decimal
    return 0 unless valid?(trinary)
   trinary.map.with_index { |n, index| n * (3 ** index) }.inject(:+)
  end

  private
  def valid?(n)
    n.to_s =~ /[012]+/
  end
end
