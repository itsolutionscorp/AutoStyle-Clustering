class Squares
  @base_nums = []

  def initialize(size)
    @base_nums = (1..size).to_a
  end

  def square_of_sums()
    @base_nums.reduce(:+) ** 2
  end 

  def sum_of_squares()
    @base_nums.map { |i| i*i}.reduce(:+)
  end

  def difference()
    # Tests give impression that always wants positive?
    (sum_of_squares - square_of_sums).abs
  end
end
