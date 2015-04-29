class Squares
  def initialize(upto)
    @upto = upto
  end

  def square_of_sums
    (1..@upto).inject {|sum, n| sum + n  } ** 2
  end

  def sum_of_squares
    (1..@upto).inject {|sum, n| sum + n**2 }
  end

  def difference
    square_of_sums()-sum_of_squares()
  end
end
