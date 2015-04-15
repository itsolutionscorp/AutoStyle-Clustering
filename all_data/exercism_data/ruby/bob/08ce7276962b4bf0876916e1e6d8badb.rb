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
    silent_treatment
  end

  def respond_to_statement?
    statement.nil? || statement.empty?
  end

  private

  def silent_treatment
    'Fine. Be that way.'
  end
end

class QuestionResponse < Response

  def respond
    asked_a_question
  end

  def respond_to_statement?
    statement.end_with?('?')
  end

  private

  def asked_a_question
    'Sure.'
  end
end

class YellingReponse < Response

  def respond
    yelled_at_me
  end

  def respond_to_statement?
    statement.match(/^[^a-z]+$/)
  end

  private

 def yelled_at_me
  'Woah, chill out!'
 end
end

class DefaultResponse < Response

  def respond
    talked_to_me
  end

  def respond_to_statement?
    statement[1..-1].match(/^[^A-Z]+$/)
  end

  private

  def talked_to_me
    'Whatever.'
  end
end
