class Grains

  SQUARE_COUNT = 64

  def square(index)
    2 ** (index - 1)
  end

  def total
    square_range.reduce(0) do |sum, index|
      sum + square(index)
    end
  end
  

  private

  def square_range
    1..SQUARE_COUNT
  end

end
