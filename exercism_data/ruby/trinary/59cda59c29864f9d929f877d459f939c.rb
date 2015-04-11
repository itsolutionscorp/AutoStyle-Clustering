class Trinary
  def initialize(trinary_str)
    @trinary_str = trinary_str
  end
  def to_decimal
    if @trinary_str =~ /[^012]/
      return 0
    else
      return convert
    end
  end
  def convert
    res = 0
    @trinary_str.reverse.chars.each_with_index do |c, index|
      res += c.to_i * (3**(index))
    end
    res
  end
end
