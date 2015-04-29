class Binary
  def initialize(string)
    @string = string
  end

  def to_decimal
  sum = 0
    @string.split("").reverse.each_with_index{|x,i|
      sum += x.to_i * 2**i
    }
  @string.gsub(/[a-zA-Z]/,"") != @string ? 0 : sum
  end
end
