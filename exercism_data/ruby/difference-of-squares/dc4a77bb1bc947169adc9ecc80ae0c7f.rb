class Squares

  def initialize(number)
    @n = number
  end

  def square_of_sums
    num = 0
    (1..@n).each { |n| num += n }
    num ** 2
  end

  def sum_of_squares
    num = 0
    (1..@n).each { |n| num += n**2 }
    num
  end

  def difference
    num = sum_of_squares - square_of_sums
    if num < 0
      num *= -1
    end
    num
  end
end
