class WordProblem
  def initialize problem
    raise ArgumentError unless problem.match(/What is -?[0-9]+( (#{OPERATORS.keys.join('|')}) -?[0-9]+)+\?/)
    @problem = problem
  end

  OPERATORS = {
    'plus' => '+',
    'minus' => '-',
    'multiplied by' => '*',
    'divided by' => '/'
  }

  def answer
    operation =
      problem
        .gsub(/What is /, '')
        .gsub(/#{OPERATORS.keys.join('|')}/, OPERATORS)
        .gsub('?', '')
    # Handling operators precedence by wrapping terms within parentheses
    op_count = problem.scan(/#{OPERATORS.keys.join('|')}/).size - 1
    op_count.times { operation.gsub!(/.+? [-+*\/] [[:digit:]]+/, '(\0)') }
    eval operation
  end

  private

  attr_reader :problem
end
