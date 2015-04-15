class Squares
  def initialize(n)
    @n = Array.new (n) {|e| e + 1 }
    @sum = 0
    @sqr = 0
    @dif = 0
  end

  def sum_of_squares
    @sum = 0
    @n.each do |e| @sum += e**2 end
    @sum
  end

  def square_of_sums
    @s = 0
    @n.each do |e| @s += e end
    @sqr = @s**2
    @sqr
  end

  def difference
    @dif = square_of_sums - sum_of_squares
    @dif
  end

end
