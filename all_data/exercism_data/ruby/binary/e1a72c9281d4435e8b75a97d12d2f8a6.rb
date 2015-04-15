class Binary
  def initialize(string)
    @num = digits(string)
  end

  def to_decimal
    @num.map.with_index{|digit, i| digit * offset(i) }.reduce(&:+)
  end

  private
  def offset(i)
    2 ** (@num.length - 1 - i)
  end

  def digits(string)
    return [0] unless string =~ /^[01]+$/
    string.chars.map(&:to_i)
  end
end
