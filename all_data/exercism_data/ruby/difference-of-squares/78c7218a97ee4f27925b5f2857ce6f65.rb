class Squares
  def initialize(n)
    @n = n + 1
  end

  def square_of_sums
    @square_of_sums ||= @n.times.reduce(&:+)**2
  end

  def sum_of_squares
    @sum_of_squares ||= @n.times.map{ |i| i**2 }.reduce(&:+)
  end

  def difference
    @diff ||= square_of_sums - sum_of_squares
  end
end
