class Grains
  def square(number_of_squares)
    @grain_count = 1

    i = 1
    while i < (number_of_squares)
      @grain_count = @grain_count * 2
      i += 1
    end
    @grain_count
  end

  def total
    square(65)-1
  end
end
