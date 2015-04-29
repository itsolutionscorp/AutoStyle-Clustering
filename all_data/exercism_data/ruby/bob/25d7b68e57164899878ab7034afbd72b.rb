class Bob

  def hey(statement)
    Response.new(statement).respond_to_statement
  end
end

class Response
  attr_reader :statement

  def initialize(statement)
    @statement = statement
  end

  def respond_to_statement
    responder = self.class.responders.map { |r|
      r.new(statement)
    }.detect { |r| r.respond_to_statement? }
    responder.respond
  end

  def self.inherited(subclass)
    @classes ||= []
    @classes << subclass
  end

  def self.responders
    @classes
  end
end

class DramaticResponse < Response

  def respond
    'Fine. Be that way.'
  end

  def respond_to_statement?
    statement.nil? || statement.empty?
  end
end

class QuestionResponse < Response

  def respond
    'Sure.'
  end

  def respond_to_statement?
    statement.end_with?('?')
  end
end

class YellingReponse < Response

  def respond
    'Woah, chill out!'
  end

  def respond_to_statement?
    statement.match(/^[^a-z]+$/)
  end
end

class DefaultResponse < Response

  def respond
    'Whatever.'
  end

  def respond_to_statement?
    statement[1..-1].match(/^[^A-Z]+$/)
  end
end
