class Grains

  def square(board_square)
    2 ** (board_square - 1)
  end

  def total
    total_grains = 0
    for i in 1..64
      total_grains += 2 ** (i-1)
    end
    total_grains
  end

end
