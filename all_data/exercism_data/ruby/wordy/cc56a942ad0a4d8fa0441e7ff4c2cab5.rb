class WordProblem
  attr_reader :problem, :call_stack, :answer

  NUMBER = /-?\d+/

  METHOD = /(?:pl|min)us|(?:multiplied|divided) by/

  PROBLEM = /#{NUMBER}|#{METHOD}/

  OPERATIONS = {
    'plus' => :+,
    'minus' => :-,
    'multiplied by' => :*,
    'divided by' => :/
  }

  def initialize(problem)
    @problem = problem.gsub(/^What is/, '')
    @call_stack = @problem.scan(PROBLEM)
    fail ArgumentError unless valid?
    @answer = nil
    calculate
  end

  def calculate
    return answer unless answer.nil?
    stack = call_stack.dup
    initial = stack.shift.to_i
    @answer = stack.each_slice(2).reduce(initial) do |value, (operation, number)|
      fail ArgumentError unless OPERATIONS.key?(operation)
      value.send(OPERATIONS[operation], number.to_i)
    end
  end

  def valid?
    call_stack.length.odd? && call_stack.length >= 3
  end
end
