# Exercism.io: Grains of rice on a chessboard problem
class Grains
  def square(square)
    2**(square - 1)
  end

  def total
    2**64 - 1
  end
end
