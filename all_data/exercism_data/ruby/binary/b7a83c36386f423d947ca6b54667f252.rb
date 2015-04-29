class Binary

  def initialize val
    @val = val
  end

  def to_decimal
    return 0 if @val.nil? or @val.match /[^01]/
    @val.reverse.each_char.with_index.reduce(0) {|s,c| s + c[0].to_i * 2**c[1] }
  end

end
