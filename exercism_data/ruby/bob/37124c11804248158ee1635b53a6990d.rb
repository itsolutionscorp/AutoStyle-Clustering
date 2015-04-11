class ResponseHandler
  def initialize(provided_response, &evaluator)
    @response = provided_response
    @evaluator = evaluator
  end
  
  def has_response?(text)
    @evaluator.nil? || @evaluator.call(text)
  end
  
  def response
    @response
  end
end

class ConversationTactic
  def self.passive_aggressive(response)
    ResponseHandler.new(response) { |text| text.empty? }
  end
  
  def self.defensive(response)
    ResponseHandler.new(response) { |text| !text.match /[a-z]/ }
  end
  
  def self.answering(response)
    ResponseHandler.new(response) { |text| text.end_with? "?" }
  end
  
  def self.unconditional(response)
    ResponseHandler.new(response)
  end
end

module Conversationalist
  def hey(text)
    find_handler(sanitize_input text).response
  end

  private
  def sanitize_input(text)
    text.nil? ? "" : text.strip
  end

  def find_handler(text)
    response_handlers.find { |handler| handler.has_response? text }
  end
end

class Bob
  include Conversationalist

  def response_handlers  
    @response_handlers ||= [
      ConversationTactic.passive_aggressive("Fine. Be that way!"),
      ConversationTactic.defensive("Woah, chill out!"),
      ConversationTactic.answering("Sure."),
      ConversationTactic.unconditional("Whatever.")
    ]
  end
end
