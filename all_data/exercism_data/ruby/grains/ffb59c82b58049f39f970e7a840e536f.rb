class Grains
  # return the count of grains for a given square
  def square(index)
    count_for_square(index)
  end

  # sum up all the grains on
  # all 64 squares on the board
  def total
    ( 1..64 ).inject do |sum, index|
      sum += count_for_square(index)
    end
  end

  private
  # recursively find the count of grains for a given square
  def count_for_square(index)
    if 1 == index
      return 1
    end
    2 * count_for_square(index-1)
  end
end
