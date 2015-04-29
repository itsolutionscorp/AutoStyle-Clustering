class WordProblem
  def initialize(question)
    @first, @actions = analyze(question)
  end

  def answer
    @actions.inject(@first) { |result, action| result.send(action.first, action.last) }
  end

private

  OPERATIONS = { "plus"          => :+,
                 "minus"         => :-,
                 "multiplied by" => :*,
                 "divided by"    => :/ }

  def analyze(question)
    question[/^What is (-?\d+)(( (#{operations_expression}) (-?\d+))+)\?$/] or raise ArgumentError
    first = $1.to_i

    actions = $2.scan(/(#{operations_expression}) (-?\d+)/).map do |operation, operand| 
      [OPERATIONS[operation], operand.to_i]
    end
    
    [first, actions]
  end

  def operations_expression
    OPERATIONS.keys.join "|"
  end
end
