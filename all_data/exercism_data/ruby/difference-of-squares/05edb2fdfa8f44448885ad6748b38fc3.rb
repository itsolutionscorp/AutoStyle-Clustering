class Squares
  attr_accessor :number

  def initialize(number)
    self.number = number
  end

  def square_of_sums
    ((1..number).to_a.inject(0, :+))**2
  end

  def sum_of_squares
    (1..number).to_a.inject(0) do | x, n|
      x += n**2
    end
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
