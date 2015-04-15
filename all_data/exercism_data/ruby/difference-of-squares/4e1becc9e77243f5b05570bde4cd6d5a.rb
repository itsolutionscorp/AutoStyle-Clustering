class Squares
  def initialize(nr)
    @nr = nr
  end

  def square_of_sums
    numbers.reduce(&:+) ** 2
  end

  def sum_of_squares
    numbers.map{|i| i ** 2}.reduce(&:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def numbers
    1.upto(@nr)
  end
end
