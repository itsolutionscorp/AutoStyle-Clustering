class Squares
  def initialize(num)
    @num = num
  end

  def square_of_sums
    sum
    @sum * @sum
  end

  def sum_of_squares
    squares
    @squares.inject{ |sum, num| sum + num}
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def sum
    values = Array.new(@num) {|num| num + 1}
    @sum = values.inject{ |sum, num| sum + num }
  end

  def squares
    values = Array.new(@num) {|num| num + 1}
    @squares = values.map{|num| num * num }
  end
end
