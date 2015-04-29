class Squares

  def initialize(num)
    @num = num
  end

  def sum_of_squares
    sum = 0
    @num.times { |i| sum += (i.next() ** 2) }
    sum
  end

  def square_of_sums
    sum = 0
    @num.times { |i| sum += i.next() }
    sum ** 2
  end

  def difference
    (sum_of_squares - square_of_sums).abs()
  end

end
