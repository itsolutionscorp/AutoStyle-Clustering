class Squares
  attr_reader :number

  def initialize(number)
    @number = number
  end

  def square_of_sums
    array = []
    sum = 0

    (0..@number).each do |n|
      array[n] = n
    end

      array.each { |a| sum += a }
    return sum * sum
  end

  def sum_of_squares
    array = []
    (0..@number).each do |n|
      array[n] = n
    end

    sum = 0
    array.each { |a| sum += a * a}
    return sum
  end

  def difference
    sum_of_squares > square_of_sums ? sum_of_squares - square_of_sums : square_of_sums - sum_of_squares
  end

end
