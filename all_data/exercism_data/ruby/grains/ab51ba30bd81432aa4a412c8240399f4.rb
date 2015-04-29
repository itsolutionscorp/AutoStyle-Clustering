class Grains

  def square(square_num)
    2 ** (square_num - 1)
  end

  def total
    total = 0
    square_num = 1

    64.times do
      total += square(square_num)
      square_num += 1
    end    
    
    total
  end
end
