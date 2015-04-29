class Message
  def initialize(text)
    @text = text
  end

  def is_upcased?
   valid_sentence? && @text.upcase == @text
  end

  def valid_sentence?
    @text =~ /[a-zA-Z].*/
  end

  def is_question?
    @text.end_with?("?")
  end

  def blank?
    @text.strip.empty?
  end
end

class Response
  attr_reader :message

  def initialize(message)
    @message = message
  end

  def valid?
    raise NotImplementedException
  end
end

class EmptyResponse < Response
  def valid?
    @message.blank?
  end

  def output
    "Fine. Be that way!"
  end
end

class DefaultResponse < Response
  def valid?
    !@message.blank?
  end

  def output
    "Whatever."
  end
end

class CapsLockResponse < Response

  def valid?
    message.valid_sentence? && message.is_upcased?
  end

  def output
    "Woah, chill out!"
  end
end

class QuestionResponse < Response
  def valid?
    !message.is_upcased? && message.is_question?
  end

  def output
    "Sure."
  end
end

class MessageResponder
  def respond(text)
    message = Message.new(text)

    valid_response = nil
    [EmptyResponse, DefaultResponse, CapsLockResponse, QuestionResponse].each do |r_klass|
      response = r_klass.new(message)
      valid_response = response if response.valid?
    end

    valid_response
  end
end

class Bob
  def initialize(options = {})
    @responder = options.fetch(:responder) { MessageResponder.new }
  end

  def hey(text)
    response = @responder.respond(text)
    response.output
  end
end
