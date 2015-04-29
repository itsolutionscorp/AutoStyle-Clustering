class Squares
  def initialize(num=0)
    @number = num
  end

  def square_of_sums
    define_number.inject(:+) ** 2
  end

  def sum_of_squares
    define_number.map{|n| n**2 }.inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def define_number
    @number_being_used ||= (0..@number)
  end
end
