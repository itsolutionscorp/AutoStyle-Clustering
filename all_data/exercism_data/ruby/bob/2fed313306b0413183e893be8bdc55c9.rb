class UpcaseMessageResponder
  
  def response
    "Woah, chill out!"
  end

  def valid_for? message
    not message.scan(/[a-zA-Z]/).empty? and message.upcase == message
  end

end

class DefaultResponder

  def response
    "Whatever."
  end

  def valid_for? message
    true
  end

end

class QuestionResponder

  def response
    "Sure."
  end

  def valid_for? message
    message.end_with? "?"
  end

end

class EmptyMessageResponder

  def response
    'Fine. Be that way!'
  end

  def valid_for? message
    message.scan(/\S/).empty?
  end

end

class Bob

  def initialize
    @responders = []
    @responders << UpcaseMessageResponder.new
    @responders << QuestionResponder.new
    @responders << EmptyMessageResponder.new
    @responders << DefaultResponder.new
  end

  def hey message
    applicable_responders_for(message).first.response
  end

  def applicable_responders_for message
    @responders.select do |responder|
      responder.valid_for? message
    end
  end

end
