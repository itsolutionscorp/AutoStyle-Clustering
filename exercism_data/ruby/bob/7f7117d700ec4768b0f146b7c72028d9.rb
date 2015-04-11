module Responder
  def respond(message)
    message = Message.new(message)
    self.class.responses[message.type] || self.class.responses[:statement]
  end

  def self.included(base)
    base.extend(ClassMethods)
  end

  module ClassMethods
    def responds_to(responses)
      @responses ||= {}
      @responses = @responses.merge(responses)
    end

    def responses
      @responses
    end
  end
end

class Message
  attr_reader :message

  def initialize(message)
    @message = message
  end

  def type
    if !message || message == ""
      :silence
    elsif message == message.upcase
      :shouting
    elsif message.end_with?("?")
      :question
    else
      :statement
    end
  end
end

class Bob
  include Responder

  responds_to silence: "Fine. Be that way!"
  responds_to shouting: "Woah, chill out!"
  responds_to question: "Sure."
  responds_to statement: "Whatever."

  alias_method :hey, :respond
end
