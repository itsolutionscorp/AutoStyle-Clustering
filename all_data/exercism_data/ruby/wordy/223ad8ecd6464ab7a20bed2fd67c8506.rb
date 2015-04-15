class WordProblem
  
  EXPRESSION_PATTERN = /-?\d+|plus|multiplied by|minus|divided by/
  QUESTION_PATTERN = /What is (?<expression>-?\d+( (plus|multiplied by|minus|divided by) -?\d+)*)\?/
  
  def initialize(phrase)
    match = QUESTION_PATTERN.match phrase
    raise ArgumentError.new('Invalid question format!') unless match
    @expression = match[:expression]    
  end
  
  def answer
    tokens = @expression.scan EXPRESSION_PATTERN
    
    current_value = parse_number(tokens.shift)
    
    while tokens.any?
      operator = parse_operator(tokens.shift)
      term = parse_number(tokens.shift)
      
      current_value = current_value.send(operator, term)
    end
    
    current_value
  end
  
  private
  
  def parse_number(num_str)
    Integer(num_str, 10)
  end
  
  def parse_operator(op_str)
    case op_str
    when 'plus'
      :+
    when 'minus'
      :-
    when 'multiplied by'
      :*
    when 'divided by'
      :/
    else
      raise ArgumentError.new("Invalid operator: [#{num_str}]")
    end
  end
end
