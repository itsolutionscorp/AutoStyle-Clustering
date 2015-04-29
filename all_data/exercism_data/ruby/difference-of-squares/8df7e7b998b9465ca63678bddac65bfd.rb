class Squares < Struct.new(:target)
  def square_of_sums
    (1..target).reduce(&:+) ** 2
  end

  def sum_of_squares
    (1..target).map {|n| n**2}.reduce(&:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
