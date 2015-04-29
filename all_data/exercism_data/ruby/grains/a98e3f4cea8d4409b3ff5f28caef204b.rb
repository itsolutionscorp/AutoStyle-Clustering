class Grains

  GRID_SIZE = 8

  def square(value)
    1 << (value) >> 1
  end

  def total
    grid.reduce(0) do |memo, v|
      memo += square(v)
    end
  end

  private

  def grid
    0..(GRID_SIZE ** 2)
  end

end
