class WordProblem
  attr_reader :problem

  def initialize(problem)
    @problem = problem
  end

  def answer
    raise ArgumentError unless number_array.length > 1
    if operations.length == 1
      single_operation_lookup[operations[0]].call(number_array[0], number_array[1])
    else
      double_operation_lookup[operations.join(",")].call(number_array[0], number_array[1], number_array[2])
    end
  end

  private

  def number_array
    problem.scan(/[-+]?\d*\,?\d+/).collect { |number| number.to_i }
  end

  def operations
    problem.split.select { |word| possible_operations.include?(word) }
  end

  def possible_operations
    ["divided", "plus", "minus", "multiplied"]
  end

  def single_operation_lookup
    {
    'plus' => lambda { |number, addend| number + addend},
    'minus' => lambda { |number, subtrahend| number - subtrahend},
    'multiplied' => lambda { |number, multiplier| number * multiplier},
    'divided' => lambda { |number, divisor| number / divisor}
    }
  end

  def double_operation_lookup
    {
      "plus,plus" => lambda { |number, addend, addend2| number + addend + addend2},
      "plus,minus" => lambda { |number, addend, subtrahend| number + addend - subtrahend},
      "minus,minus" => lambda { |number, subtrahend, subtrahend2| number - subtrahend - subtrahend2},
      "minus,plus" => lambda { |number, subtrahend, addend| number - subtrahend + addend},
      "minus,plus" => lambda { |number, subtrahend, addend| number - subtrahend + addend},
      "multiplied,multiplied" => lambda { |number, multiplier, multiplier2| number * multiplier * multiplier2},      
      "divided,divided" => lambda { |number, divisor, divisor2| number / divisor / divisor2},      
      "plus,multiplied" => lambda { |number, addend, multiplier| (number + addend) * multiplier}      
    }
  end

end
