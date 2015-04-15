class Expression
  def initialize(message)
    @message = message
  end

  private

  attr_reader :message
end

class Silence < Expression
  def match?
    message.strip.empty?
  end

  def response
    'Fine. Be that way!'
  end
end

class Question < Expression
  def match?
    message.end_with?('?')
  end

  def response
    'Sure.'
  end
end

class Yell < Expression
  def match?
    message.upcase == message
  end

  def response
    'Woah, chill out!'
  end
end

class Whatever < Expression
  def match?
    true
  end

  def response
    'Whatever.'
  end
end

class Bob
  EXPRESSIONS = [ Silence, Yell, Question, Whatever ].freeze

  def hey(message)
    expression = find_expression(message)
    expression.response
  end

  private

  def find_expression(message)
    EXPRESSIONS.map{ |klass| klass.new(message) }.find(&:match?)
  end
end
