require 'pry'
class Squares
  attr_reader :number
  def initialize(number)
    @number = number
  end

  def sum_of_squares
    answer = 0
    number.times { |x| answer += (x + 1)**2 }
    return answer
  end

  def square_of_sums
    answer = 0
    number.times { |x| answer += (x + 1) }
    return answer**2
  end

  def difference
    return (square_of_sums - sum_of_squares).abs

  end

end
