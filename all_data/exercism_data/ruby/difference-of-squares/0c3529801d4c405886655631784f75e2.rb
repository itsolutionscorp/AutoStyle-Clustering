class Squares
  def initialize(num)
    @num = num
  end

  def square_of_sums
    sum ** square
  end

  def sum_of_squares
    sum(square)
  end

  def difference
    (square_of_sums - sum_of_squares).abs
  end

  private
  attr_reader :num

  def sum(optional=1)
    (0..num).inject { |sum, number| sum + number ** optional}
  end

  def square
    2
  end
end

# class Squares
#   def initialize(num)
#     @num = num
#   end

#   def square_of_sums
#     sum
#     square
#   end

#   def sum_of_squares
#     square
#     sum
#   end

#   def difference
#     (square_of_sums - sum_of_squares).abs
#   end

#   private
#   attr_reader :num

#   def sum
#     numbers.reduce { |total, number| total + number }
#   end

#   def square
#     numbers ** 2
#     # numbers.map! { |number| number ** 2 }
#   end

#   def numbers
#     (0..num).to_a
#   end
# end
