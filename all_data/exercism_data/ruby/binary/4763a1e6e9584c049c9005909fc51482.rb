class Binary
  def initialize(binary)
  	@binary = binary
  end

  def to_decimal
  	return 0 if @binary =~ /[^01]/
  	@binary.reverse.split('').map.with_index { |b, i|  b.to_i*(2**i) }.inject(:+)		                     
  end
end
