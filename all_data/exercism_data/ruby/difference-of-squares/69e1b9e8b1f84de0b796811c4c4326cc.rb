class Squares
  @bound = 0
  def initialize(bound)
    @bound = bound
  end

  def square_of_sums()
    total = 0
    (0..@bound).each do |i|
      total += i
    end 
    total**2
  end

  def sum_of_squares()
    total = 0
    (0..@bound).each do |i|
      total += i**2
    end 
    total
  end

  def difference()
    square_of_sums - sum_of_squares
  end
end
