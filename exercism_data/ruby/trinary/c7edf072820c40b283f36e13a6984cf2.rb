class Trinary
  THIS_ARY = 3

  def initialize(num)
    @num = num.to_i
  end

  def to_decimal
    result = 0
    while @num > 0
      if @num.to_s.size == 1
        result += 1
        @num -= 1
      else
        result +=  THIS_ARY ** (@num.to_s.size - 1)
        @num -= 10 ** (@num.to_s.size - 1)
      end
    end
    result
  end

end
