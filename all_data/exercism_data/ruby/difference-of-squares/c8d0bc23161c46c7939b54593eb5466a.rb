class Squares
  attr_accessor :number

  def initialize(number)
    @number = number
  end

  def square_of_sums
    range.inject(:+) ** 2
  end

  def sum_of_squares
    range.map{|num| num ** 2}.inject(:+)
  end

  def difference
    (sum_of_squares - square_of_sums).abs
  end

  private

    def range
      (1..number)
    end
end
