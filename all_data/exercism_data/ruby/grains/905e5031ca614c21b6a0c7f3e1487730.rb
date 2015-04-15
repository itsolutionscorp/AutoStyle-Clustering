class Grains
  def square number
    @total_on_board = 1
    (1..number).inject{|total_on_square, n| 
      total_on_square *= 2 
      @total_on_board += total_on_square
      total_on_square
    }
  end
  def total
    square 64
    @total_on_board
  end
end
