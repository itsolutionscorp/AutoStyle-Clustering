class Grains
  def initialize(board_size=8)
    @num_of_squares = board_size**2
  end
  def square(n)
    2**(n-1)
  end

  def total
    1.upto(@num_of_squares).inject(0) do |sum, square_number|
      sum += square(square_number)
    end
  end
end
