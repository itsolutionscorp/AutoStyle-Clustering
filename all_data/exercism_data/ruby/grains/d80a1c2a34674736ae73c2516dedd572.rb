# implements rules of the grains challenge
class Grains
  # returns the number of grains on a square
  def square(space_number)
    space_number == 1 ? 1 : 2 * square(space_number - 1)
  end

  # returns the number of grains on a 64 space chessboard
  def total
    (1..64).reduce { |sum, space_number| sum + square(space_number) }
  end
end
