class Grains
  def square(square)
    x = 1
    @total = 1
    (square - 1).times do
      x = x * 2
      @total += x
    end
    x
  end
  def total
    self.square(64)
    @total
  end
end
