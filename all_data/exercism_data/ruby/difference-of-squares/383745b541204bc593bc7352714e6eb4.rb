class Squares
  attr_accessor :base_number, :sqos, :sosq

  def initialize n
    @base_number = n
    @sosq = @sqos = 0
    1.upto(n) do |i|
      @sosq += i**2
      @sqos += i
    end
    @sqos = @sqos**2
  end

  def square_of_sums; @sqos; end
  def sum_of_squares; @sosq; end

  def difference
    square_of_sums - sum_of_squares
  end
end
