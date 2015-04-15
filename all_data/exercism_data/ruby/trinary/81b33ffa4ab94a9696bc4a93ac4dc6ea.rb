class Trinary
  def initialize(trinary)
    @trinary = trinary
  end

  def to_decimal
    return 0 if @trinary =~ /[^0-2]/
    @trinary.reverse.chars.map.with_index { |e,i| e.to_i * (3**i) }.inject(:+)
  end
end
