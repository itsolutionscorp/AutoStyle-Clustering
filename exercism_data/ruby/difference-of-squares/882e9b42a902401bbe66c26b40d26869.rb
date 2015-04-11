class Squares
  attr_reader :items

  def initialize(items)
    @items = (1..items) 
  end

  def square_of_sums
    items.reduce(:+) ** 2
  end

  def sum_of_squares
    items.reduce {|accu,x| accu + x**2}
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
