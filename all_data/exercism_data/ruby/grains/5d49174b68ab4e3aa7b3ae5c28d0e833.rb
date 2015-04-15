class Grains
  def square index
    2 ** (index - 1)
  end

  def total
    all_squares.reduce(:+)
  end

  private

  def all_squares
    (1..64).map {|index| square(index) }
  end

end
