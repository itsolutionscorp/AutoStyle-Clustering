class Squares
  attr_reader :number
  
  def initialize(input_number) 
    @number = input_number
  end

  def square_of_sums
    (1..@number).reduce(0) do |result, current_number|
      result += current_number
    end ** 2
  end

  def sum_of_squares
    (1..@number).reduce(0) do |result, current_number|
      result += (current_number ** 2)
    end
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end

end
