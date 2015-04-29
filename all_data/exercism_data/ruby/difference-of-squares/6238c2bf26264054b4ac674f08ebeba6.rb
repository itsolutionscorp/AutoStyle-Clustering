class Squares
  def initialize(number)
    @number = number
  end

  def square_of_sums
    array = []
    new_array = []
    counter = 1
    array << @number
    new_array << @number
    while counter < @number
      array.each do |number|
        new_array << (number - counter)
        counter += 1
      end
    end
    new_array.inject { |sum, number| sum + number } ** 2
  end

  def sum_of_squares
    array = []
    new_array = []
    counter = 1
    array << @number
    new_array << @number
    while counter < @number
      array.each do |number|
        new_array << (number - counter)
        counter += 1
      end
    end
    total_sum = 0
    new_array.each do |number|
      total_sum += (number ** 2)
    end
    total_sum
  end
  
  def difference
    square_of_sums - sum_of_squares
  end
end
