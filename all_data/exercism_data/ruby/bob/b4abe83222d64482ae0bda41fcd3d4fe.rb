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
    self.class.responders.each { |r|
      if response = r.new(statement).respond
        return response
      end
    }
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

  def silent_treatment
    if statement.nil?
      'Fine. Be that way.'
    elsif statement.empty?
      'Fine. Be that way.'
    end
  end
end

class QuestionResponse < Response

  def respond
    asked_a_question
  end

  private

  def asked_a_question
    if statement.end_with?('?')
      'Sure.'
    end
  end
end

class YellingReponse < Response

  def respond
   yelled_at_me
  end

  private

 def yelled_at_me
   if statement.match(/^[^a-z]+$/)
    'Woah, chill out!'
   end
 end
end

class DefaultResponse < Response

  def respond
    talked_to_me
  end

  private

  def talked_to_me
    if statement[1..-1].match(/^[^A-Z]+$/)
      'Whatever.'
    end
  end
end
