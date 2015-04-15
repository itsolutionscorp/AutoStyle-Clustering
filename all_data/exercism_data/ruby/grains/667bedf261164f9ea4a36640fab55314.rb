class Grains

  # the number of grains on the virtual 65th tile (minus 1) is conveniently 
  # the sum of all grains on our 64 tile chessboard.
  INPUT_MAX = 65    

  def square(num_squares)
    if num_squares <= INPUT_MAX 
       1 << (num_squares - 1)
    else
      raise "#{num_squares} is not within the 1 <= n < #{INPUT_MAX} range." 
    end
  end

  def total
    square(INPUT_MAX) - 1
  end

end
