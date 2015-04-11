class Binary
  def initialize(num)
    @num = num
  end

  def to_decimal
    return 0 if @num.to_i == 0
    sum = 0
    @num.chars.each_with_index do |x, i|
      sum += x.to_i * 2 ** (@num.length - (i + 1))
    end
    sum
  end
end
