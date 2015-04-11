class Grains

  def initialize(growth_ratio = 2, num_of_squares = 64)
    @growth_ratio = growth_ratio
    @num_of_squares = num_of_squares
  end

  def square(n)
    @growth_ratio ** (n - 1)
  end
  
  def total
    @total ||= sum_of_geom_prog(@growth_ratio, (1..@num_of_squares))
  end

  private

  def sum_of_geom_prog(ratio, range)
    range.begin * ((ratio ** range.end - 1)/ (ratio - 1))
  end

end
