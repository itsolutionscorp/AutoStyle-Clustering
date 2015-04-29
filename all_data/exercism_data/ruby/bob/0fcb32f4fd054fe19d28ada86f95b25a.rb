module Conversationalist
  def provide_response_handlers(handler_classes)
    @response_handlers = handler_classes.map { |handler_class| handler_class.new }
  end
  
  def hey(text)
    find_handler(sanitize_input text).response  
  end

  private
  def sanitize_input(text)
    text.strip unless text.nil?
  end

  def find_handler(text)
    @response_handlers.find { |handler| handler.has_response? text }
  end
end

class Bob
  include Conversationalist

  def initialize
    provide_response_handlers [ AngryResponseHandler, DefensiveResponseHandler, QuestionResponseHandler, DefaultResponseHandler ]
  end
end

class AngryResponseHandler
  def has_response?(text)
    text.nil? or text.empty?
  end

  def response
    "Fine. Be that way!"
  end
end

class DefensiveResponseHandler
  def has_response?(text)
    !text.match /[a-z]/
  end

  def response
    "Woah, chill out!"
  end
end

class QuestionResponseHandler
  def has_response?(text)
    text.end_with? "?"
  end

  def response
    "Sure."
  end
end

class DefaultResponseHandler
  def has_response?(text)
    true
  end

  def response
    "Whatever."
  end
end
