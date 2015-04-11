class Grains
  def square(num)
  	@chess_board = [1]
  	p = 1
  	(num-1).times {@chess_board << p *= 2}
  	@chess_board[-1]
  end

  def total 
  	square(64)
  	@chess_board.inject(:+)
  end
end
