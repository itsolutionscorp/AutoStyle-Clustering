class Grains

  def square(square_num)
    calculate_grains(square_num)
  end

  def board_grain_totals
    board ||= {1 => 1}
    total_squares = (2..64)
    total_squares.each do |square|
      board[square] = (board[(square - 1)]) * 2
    end
    board
  end

  def calculate_grains(square_num)
    board_grain_totals[square_num]
  end

  def total
    board_grain_totals.values.inject{|sum, x| sum + x }
  end
end
