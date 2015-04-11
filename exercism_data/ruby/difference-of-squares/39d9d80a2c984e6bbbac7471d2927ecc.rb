class Squares

  def initialize(int)
    @int = int
  end

  def square_of_sums
    ((@int * (@int + 1)) / 2)  ** 2
  end

  def sum_of_squares
    (1..@int).inject(0) {|sum, n| sum += n ** 2}
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
