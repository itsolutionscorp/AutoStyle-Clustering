module SymbolExtensions
  refine Symbol do
    def to_predicate
      modify_as_string do |string|
        string << '?'
      end
    end

    def modify_as_string
      yield(to_s).to_sym
    end
  end
end

require 'delegate'
class Bob
  def initialize
    @responses = {
      shouting: 'Woah, chill out!',
      question: 'Sure.',
      silent: 'Fine. Be that way!'
    }
    @responses.default = 'Whatever.'
  end

  def hey(message)
    Responder.new(responses).respond_to(message)
  end

  private

  attr_reader :responses
end

class Responder < SimpleDelegator
  using SymbolExtensions

  def respond_to(message)
    self[message_type(message)]
  end

  private

  def message_type(message)
    message_types.select do |message_type|
      Message.new(message).send(message_type.to_predicate)
    end.first
  end

  def message_types
    keys
  end
end

class Message < SimpleDelegator
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
