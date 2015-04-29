class Grains
  def square(square)
    @total = 1
    i = 1
    wheat = 1
    while i < square
      wheat = wheat * 2
      @total += wheat
      i += 1
    end
    wheat
  end
  def total
    square(64)
    @total
  end
end
