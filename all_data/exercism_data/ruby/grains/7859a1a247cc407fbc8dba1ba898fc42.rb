class Grains

  NUM_SQUARES = 64

  def square(square)
    2 ** (square - 1)
  end

  def total
    sum = 1
    last_square = 1
    (2..NUM_SQUARES).each do |num|
      last_square *= 2
      sum += last_square
    end
    sum
  end
  
end
