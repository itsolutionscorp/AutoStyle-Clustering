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
    _count_for_square(index,1)
  end

  # tail call recursive
  def _count_for_square(index, acc=1)
    if 1 == index
      return acc
    end
    _count_for_square(index-1, 2*acc)
  end
end
