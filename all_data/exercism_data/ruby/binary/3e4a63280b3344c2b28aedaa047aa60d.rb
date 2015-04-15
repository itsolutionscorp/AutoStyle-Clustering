class Binary
  def initialize(num)
    @num = num
    @decimal = 0
  end

  def to_decimal
    x = @num.length
    i = 0
    while x > 0 do
      if @num[i] == "1"
        @decimal += 2 ** (x - 1)
      end
      x -= 1
      i += 1
    end
    @decimal
  end
end
