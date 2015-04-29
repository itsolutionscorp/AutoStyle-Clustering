class Squares

  def initialize(count)
    @count = count
  end

  def sum_of_squares
    (0..@count).inject do |mem, var|
      mem + var**2
    end
  end

  def square_of_sums
    sum = (0..@count).inject do |mem, var|
      mem + var
    end
    sum**2
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
