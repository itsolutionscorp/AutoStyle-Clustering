class WordProblem
  OPERATORS =
    { 'plus' => :+, 'minus' => :-, 'multiplied by' => :*, 'divided by' => :/ }

  attr_reader :answer

  def initialize(sentence)
    first, *rest = sentence.scan(/-?\d+|#{OPERATORS.keys.join '|'}/)
    fail ArgumentError if rest.count < 2

    @answer = rest.each_slice(2).reduce(Integer(first)) do |a, (op, x)|
      a.send(OPERATORS.fetch(op), Integer(x))
    end
  end
end
