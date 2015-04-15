class Squares
  @x = 0

  def initialize(number)
    @x = number
  end

  def square_of_sums
    sum = 0
    (1..@x).each do |i|
      sum += i
    end
    #sum = (1..@x).inject(0) { |sum, n| sum + n }

    sum **2
  end

  def sum_of_squares
    sum = 0
    (1..@x).each do |i|
      sum += i**2
    end
    sum
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
