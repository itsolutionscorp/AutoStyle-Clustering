class Binary

  def initialize(b)
    @b = b
  end
  
  def to_decimal
    if (@b !~ /^[0|1]+$/)
      return 0
    end
    d = 0
    @b.reverse.each_char.with_index { |i, index| d += (i.to_i * (2 ** index)) }
    d
  end

end
