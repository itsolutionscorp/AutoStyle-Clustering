class Binary
  attr_reader :str

  def initialize(str)
    @str = str
  end

  def to_decimal
    unless /[^0-1]/ =~ str
      arr = str.reverse.chars.map { |x| x.to_i }
      arr.each_with_index.map { |n, i| (2 ** i) * n }.inject(:+)
    else
      0
    end
  end
end
