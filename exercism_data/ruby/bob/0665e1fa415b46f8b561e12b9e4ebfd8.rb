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
      (self.to_s << '?').to_sym
    end
  end
end

module ModuleExtensions
  refine Module do
    def public_predicate_methods(include_super=true)
      public_instance_methods(include_super).map do |method|
        method.to_s.match(/(.+)\?/) do |match|
          match[0].chop.to_sym
        end
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
    public_predicate_methods(false)
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

  private

  def ranked_types
    Message.types.select do |type|
      send(type.to_predicate)
    end + [:default]
  end

  attr_reader :message
end
