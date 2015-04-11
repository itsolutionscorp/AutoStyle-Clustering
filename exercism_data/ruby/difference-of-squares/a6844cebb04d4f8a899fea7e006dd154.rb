class Squares
  def initialize (in_num)
    @in_num = in_num
  end

  def square_of_sums
    total = 0
    (1..@in_num).each {|n| total+=n}
    total**2
  end
  def sum_of_squares
    total = 0
    (1..@in_num).each {|n| total+=(n**2)}
    total
  end
  def difference
    self.square_of_sums - self.sum_of_squares
  end
end
puts Squares.new(5).difference
