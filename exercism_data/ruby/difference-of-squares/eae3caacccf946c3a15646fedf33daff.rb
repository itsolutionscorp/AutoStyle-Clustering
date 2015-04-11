class Squares < Struct.new(:n)
  def square_of_sums
    sequence.reduce(:+) ** 2
  end

  def sum_of_squares
    sequence.lazy.map{|x| x ** 2}.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def sequence
    (1..n)
  end

end
