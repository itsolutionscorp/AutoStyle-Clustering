class Squares

  def initialize(num)
    @num = num
    @square_of_sums = 0
    @sum_of_squares = 0

    calc_sum_of_squares()
    calc_square_of_sums()
  end

  def calc_sum_of_squares()
    @num.times { |i| @sum_of_squares += (i.next() ** 2) }
    @sum_of_squares
  end

  def calc_square_of_sums
    @num.times { |i| @square_of_sums += i.next() }
    @square_of_sums = @square_of_sums ** 2
  end

  def difference
    (@sum_of_squares - @square_of_sums).abs()
  end

  attr_reader :sum_of_squares
  attr_reader :square_of_sums

end
