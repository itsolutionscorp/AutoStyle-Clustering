class Binary

  def initialize val
    @val = val
  end

  def to_decimal
    return 0 if @val.nil? or @val.match /[^01]/
    @val.reverse.each_char.with_index.reduce(0) {|s,(c,i)| s + c.to_i * 2**i }
  end

end
