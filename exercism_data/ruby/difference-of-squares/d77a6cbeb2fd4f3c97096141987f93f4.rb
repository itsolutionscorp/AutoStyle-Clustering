class Squares
  attr_accessor :value

  def initialize(number)
    @value = number  
  end

  def square_of_sums
    (1...value + 1).to_a.reduce(:+)**2
  end
    
  def sum_of_squares
    (1...value + 1).to_a.map{|x| x**2 }.reduce(:+)
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end
end
