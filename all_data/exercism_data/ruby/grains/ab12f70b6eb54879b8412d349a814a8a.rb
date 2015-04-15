class Grains

  SQUARE_COUNT = 64

  def square(index)
    2 ** (index - 1)
  end

  def old_total
    square_range.reduce(0) do |sum, index|
      sum + square(index)
    end
  end

  def total
    (2 ** SQUARE_COUNT) - 1
  end


  private

  def square_range
    1..SQUARE_COUNT
  end

end
