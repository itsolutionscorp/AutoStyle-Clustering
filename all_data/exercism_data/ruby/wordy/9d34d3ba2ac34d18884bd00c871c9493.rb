class WordProblem
  OPERATORS = {
    "plus" => :+,
    "minus" => :-,
    "multipliedby" => :*,
    "dividedby" => :/
  }

  def initialize(problem)
    @problem = problem
  end

  def answer
    tokens = get_tokens
    tokens.each_slice(2).reduce(tokens.shift) do |result, (operator, operand)|
      result.public_send(operator, operand)
    end
  end

  private

  def get_tokens
    normalized_input.map.with_index { |token, i|
      if i.even?
        Integer(token)
      else
        get_operator(token)
      end
    }
  end

  def normalized_input
    input = @problem.sub(/^What is /, "")
    input = input.sub(/\?$/, "")
    input.gsub!(/\s+/, "")
    input.split(/([a-z]+)/)
  end

  def get_operator(name)
    OPERATORS.fetch(name) { raise ArgumentError }
  end
end
