class Year
  def initialize(num)
    @num = num
  end

  def leap?
    divisor = (@num % 100 == 0) ? 400 : 4
    @num % divisor == 0
  end
end
