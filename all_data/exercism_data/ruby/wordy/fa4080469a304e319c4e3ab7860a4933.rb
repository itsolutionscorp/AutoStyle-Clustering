class WordProblem
  OPERATORS = {
    "plus"          => :+,
    "minus"         => :-,
    "multiplied by" => :*,
    "divided by"    => :/,
  }

  NUMBER = /-?\d+/
  OPERATOR = Regexp.union(*OPERATORS.keys)
  EXPRESSION = /(#{NUMBER} #{OPERATOR} )+#{NUMBER}/

  def initialize(question)
    @question = question
  end

  def answer
    match = @question.match(/\AWhat is (#{EXPRESSION})\?\z/)
    raise ArgumentError, "Can't parse." unless match

    expression = match.captures.first

    while expression.include?(" ") do
      expression.sub!(/(#{NUMBER}) (#{OPERATOR}) (#{NUMBER})/) {
        a, operator, b = $1, OPERATORS.fetch($2), $3
        a.to_i.public_send(operator, b.to_i)
      }
    end

    expression.to_i
  end
end
