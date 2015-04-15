class Binary
  def initialize(binary)
    if binary =~ /[\D]/
      @binary = 0
    else
      @binary = binary
    end
  end

  def to_decimal
    decimal = @binary.to_s.reverse.chars.map.with_index{ |x,y| x.to_i * 2**y }.inject(:+)
  end
end
