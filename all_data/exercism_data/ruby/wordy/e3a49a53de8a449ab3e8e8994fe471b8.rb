class WordProblem
  attr_reader :a, :operators, :operands

  OPERATOR = {
    "plus" => :+,
    "minus" => :-,
    "divided by" => :/,
    "times" => :*,
    "multiplied by" => :*
  }

  def initialize(question)
    raise ArgumentError, "Too Advanced!" unless valid? question

    @question = question
  end

  def answer
    @answer ||= calc_answer
  end

  def operands
    @operands ||= get_operands
  end

  def operators
    @operators ||= get_operators
  end

  private
  def valid?(question)
    question.match Regexp.new "What is #{match_operand}" +
      "( #{match_operator} #{match_operand})+\\?"
  end

  def match_operand
    "-?\\d+"
  end

  def match_operator
    "[a-z\\s]+"
  end

  def get_operands
    @question.scan(/-?\d+/).map { |number| number.to_i }
  end

  def get_operators
    @question.scan(/\d\s+[a-z\s]+/).map do |operator_string|
      operator_chars = operator_string.chars
      operator_chars.shift
      op(operator_chars.join.strip)
    end
  end

  def op(operator_name)
    OPERATOR[operator_name]
  end

  def calc_answer
    i = 0
    operands.reduce do |value, number|
      value = value.send operators[i], number
      i += 1
      value
    end
  end

end
