module Responder
  def respond(message)
    message = Message.new(message)
    self.class.responses.fetch(message.type, self.class.responses[:default])
  end

  def self.included(base)
    base.extend(ClassMethods)
    base.responses = { default: "" }
  end

  module ClassMethods
    def responds_to(responses)
      @responses = @responses.merge(responses)
    end

    def default_response(response)
      @responses[:default] = response
    end

    attr_accessor :responses
  end
end

class Message
  def initialize(message)
    @message = message.to_s
  end

  def type
    if @message.empty?
      :silence
    elsif @message == @message.upcase
      :shouting
    elsif @message.end_with?("?")
      :question
    else
      :default
    end
  end
end

class Bob
  include Responder

  responds_to silence: "Fine. Be that way!"
  responds_to shouting: "Woah, chill out!"
  responds_to question: "Sure."
  default_response "Whatever."

  alias_method :hey, :respond
end
