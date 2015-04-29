class Bob
  def initialize
    responses = {
      shouting: 'Woah, chill out!',
      question: 'Sure.',
      silent: 'Fine. Be that way!'
    }
    responses.default = 'Whatever.'
    @responder = Responder.new(responses)
  end

  def hey(message)
    @responder.respond_to(message)
  end
end

require 'delegate'
class Responder < SimpleDelegator
  def respond_to(message)
    response_for(message_type(message))
  end

  private

  def response_for(message_type)
    self[message_type]
  end

  def message_types
    keys
  end

  def message_type(message)
    message_types.find do |message_type|
      Message.new(message).is?(message_type)
    end
  end
end

module SymbolExtensions
  refine Symbol do
    def to_predicate
      (to_s << "?").to_sym
    end
  end
end

class Message < SimpleDelegator
  using SymbolExtensions

  def is?(message_type)
    send(message_type.to_predicate)
  end

  def shouting?
    upcase == self && downcase != self
  end

  def question?
    end_with?('?')
  end

  def silent?
    strip.empty?
  end
end
