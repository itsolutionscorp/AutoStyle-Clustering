class Grains

  def square(square_num)
    grains_square(square_num)
  end

  def total
    total_grains = 0
    total_squares = 64
  
    (1..total_squares).each do |square_num| 
      grains_square = grains_square(square_num)
      total_grains += grains_square
    end

    total_grains
  end

  private

    def grains_square(square_num)
      (2**square_num)/2
    end

end
