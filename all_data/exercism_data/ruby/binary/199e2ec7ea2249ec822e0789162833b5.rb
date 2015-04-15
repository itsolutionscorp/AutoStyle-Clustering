class Binary
  def initialize(bnum)
  	@bnum = bnum
  end

  def to_decimal
  	return 0 if @bnum =~ /[A-z]/
  	@bnum.reverse.split('').map.with_index { |b, i|  b.to_i*(2**i) }.inject(:+)		                     
  end
end
