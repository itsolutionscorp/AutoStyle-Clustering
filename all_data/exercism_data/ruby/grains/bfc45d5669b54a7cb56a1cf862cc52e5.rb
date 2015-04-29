class Grains

  def square(chess_square_number)
    2**(chess_square_number-1)
  end

  def total
    @total = (1..64).inject(0) do |sum_amount, chess_square_number|
      sum_amount + square(chess_square_number)
    end
  end

end
