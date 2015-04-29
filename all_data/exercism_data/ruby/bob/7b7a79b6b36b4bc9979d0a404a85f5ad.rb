require 'delegate'
class Bob
  def hey(message)
    message_type = Message.new(message).type
    responses[message_type]
  end

  def responses
    {
      shouting: 'Woah, chill out!',
      question: 'Sure.',
      silent: 'Fine. Be that way!',
      default: 'Whatever.'
    }
  end
end

module SymbolExtensions
  refine Symbol do
    def to_predicate
      modify_as_string do |string|
        string << '?'
      end
    end

    def modify_as_string
      yield(self.to_s).to_sym
    end
  end
end

module ModuleExtensions
  refine Module do
    def public_predicate_methods(include_super=true)
      public_instance_methods(include_super).select do |method|
        method.to_s.end_with?('?')
      end.compact
    end
  end
end

class Message
  using ModuleExtensions
  using SymbolExtensions

  def initialize(message)
    @message = message
  end

  def self.types
    public_predicate_methods(false).map do |method|
      method.modify_as_string do |string|
        string.chop
      end
    end
  end

  def type
    ranked_types = Message.types.select { |type| send(type.to_predicate) }
    ranked_types.push(:default).first
  end

  def shouting?
    message.upcase == message && message.downcase != message
  end

  def question?
    message.end_with?('?')
  end

  def silent?
    message.strip.empty?
  end

  attr_reader :message
end
