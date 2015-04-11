class Binary
  attr_accessor :bn

  def initialize bn
    @bn = bn
  end

  def to_decimal
    return 0 if bn.match(/\D/)
    bn.each_char.to_a.map{ |i| i.to_i }.reverse.map.with_index {|e, i| e*(2**i) }.reduce(:+)
  end
end

#puts Binary.new('101').to_decimal
