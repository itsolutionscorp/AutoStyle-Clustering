class Binary
  def initialize(str)
    @binary = str.match(/[a-z]/)?\
      Array.new(0) : str.reverse.split(//).map{|i| i.to_i}
  end

  def to_decimal
    @result = 0
    @binary.each_with_index{|n, i| @result += (n * (2**i))}
    @result
    #@binary.each_with_object(o = 0){|(n,i),o| o += (n * (2**i))}
  end
end
