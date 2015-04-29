class Bob
  def hey(message)
    Conversation.new(message).respond
  end
end

class Conversation
  attr_reader :message

  def initialize(message)
    @message = message
  end

  def respond
    blank_response || shouting_response || question_response || default_response
  end

  private

  def blank_response
    Response.new("Fine. Be that way!", message).reply_to { |message| message.nil? || message.empty? }
  end

  def shouting_response
    Response.new("Woah, chill out!", message).reply_to { |message| message.upcase == message }
  end

  def question_response
    Response.new("Sure.", message).reply_to { |message| message.end_with?('?') }
  end

  def default_response
    Response.new("Whatever.", message).reply_to { true }
  end
end

class Response
  attr_reader :text, :message

  def initialize(text, message)
    @text = text
    @message = message
  end

  def reply_to(&block)
    text if yield message
  end
end
