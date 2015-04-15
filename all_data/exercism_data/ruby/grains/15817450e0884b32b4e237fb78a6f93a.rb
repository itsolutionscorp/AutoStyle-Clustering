class Grains

  def square(square_num)
    2 ** (square_num - 1)
  end

  def total
    total = 0
    square_num = 1

    (1..64).inject { |total, square_num| total += square(square_num) }
 
    
    total
  end
end
