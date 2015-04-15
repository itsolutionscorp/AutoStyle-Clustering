class Trinary
  def initialize(trinary_num)
    @trinary_num = trinary_num
    @trinary_num = '0' unless @trinary_num =~ /^[012]+$/
  end

  def to_decimal
    @trinary_num.reverse.chars.map.with_index { |x, i| x.to_i * 3**i }.inject(:+)
  end
end
