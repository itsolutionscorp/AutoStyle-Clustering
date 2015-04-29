class WordProblem
  def initialize(question)
    @first, @actions = analyze(question)
  end

  def answer
    @actions.inject(@first) { |result, action| result.send(action.operation, action.operand) }
  end

private

  Action = Struct.new(:operation, :operand)

  OPERATIONS = { "plus"          => :+,
                 "minus"         => :-,
                 "multiplied by" => :*,
                 "divided by"    => :/ }

  def analyze(question)
    first, parts = scan_question(question)
    [first, scan_parts(parts)]
  end

  def scan_question(question)
    question.match /^What is #{number_regex}((#{operation_regex})+)\?$/ or raise ArgumentError
    [$1.to_i, $2]    
  end

  def scan_parts(parts)
    parts.scan(/#{operation_regex}/).map do |operation_word, operand| 
      Action.new OPERATIONS[operation_word], operand.to_i
    end
  end

  def operation_regex
    / (#{OPERATIONS.keys.join "|"}) #{number_regex}/
  end

  def number_regex
    /(-?\d+)/
  end
end
