class Squares
  def initialize(n)
    @range = n
  end

  def square_of_sums
    output = 0
    (1..@range).each do |i|
      output += i
    end
    return output**2
  end

  def sum_of_squares
    output = 0
    (1..@range).each do |i|
      output += i**2
    end
    return output
  end

  def difference
    return square_of_sums - sum_of_squares
  end
end
