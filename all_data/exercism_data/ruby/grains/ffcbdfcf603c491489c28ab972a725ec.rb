class Grains
  def square num
    2**(num - 1)
  end

  def total num_of_squares=64
    square(num_of_squares + 1) - 1
  end
end
