# Class for counting the number of grains of rice on a chessboard, accourding
# to an old legend.
class Grains
  # Calculates the number of rice grains on a given chessboard space.
  #
  # @param space [Integer] the space on the chessboard
  # @return [Integer] the number of grains of rice on the space.
  def square(space)
    2 ** (space - 1)
  end

  # Calculates the total number of rice grains on the chessboard.
  #
  # @return [Integer] the total number of grains of rice on the chessboard.
  def total
    (1..64).to_a.map { |total| square(total) }.inject(:+)
  end
end
