class WordProblem
  attr_reader :problem, :number_array

  def initialize(problem)
    @problem = problem
  end

  def answer 
    raise ArgumentError unless number_array.length > 1
    if text_array.length >= 8
      multiply_or_divide(text_array[3], text_array[-3])
    elsif number_array.length <= 2
      perform_operation(text_array[3])
    else 
      perform_two_operations(text_array[3], text_array[-2])
    end
  end

  def perform_operation(operation)
    case operation
      when "plus"  
        add
      when "minus"
        subtract
      when "multiplied"
        multiply
      when "divided"
        divide
    end
  end

  def perform_two_operations(operation1, operation2)
    if operation1 == "plus" && operation2 == "plus"
      number_array.inject(:+)
    elsif operation1 == "plus" && operation2 == "minus"
      number_array[0] + number_array[1] - number_array[2]
    elsif operation1 == "minus" && operation2 == "minus"
      number_array[0] - number_array[1] - number_array[2]
    elsif operation1 == "minus" && operation2 == "plus"
      number_array[0] - number_array[1] + number_array[2]
    elsif operation1 == "multiplied" && operation2 == "plus"
      number_array[0] - number_array[1] + number_array[2]
    end
  end

  def multiply_or_divide(operation1, operation2)
    if operation1 == "multiplied" && operation2 == "multiplied"
      number_array[0] * number_array[1] * number_array[2]
    elsif operation1 == "plus" && operation2 == "multiplied"
      (number_array[0] + number_array[1]) * number_array[2]
    elsif operation1 == "divided" && operation2 == "divided"
      (number_array[0] / number_array[1]) / number_array[2]
    end
  end

  private

  def text_array
    problem.gsub("?","").split
  end

  def number_array
    problem.scan(/[-+]?\d*\,?\d+/).collect { |number| number.to_i }
  end

  def add
    number_array[0] + number_array[1]
  end

  def subtract
    number_array[0] - number_array[1]
  end

  def multiply
    number_array[0] * number_array[1]
  end

  def divide
    number_array[0] / number_array[1]
  end

end
