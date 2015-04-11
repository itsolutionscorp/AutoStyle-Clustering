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


class Message
  def initialize(message)
    @message = message
  end

  def self.types
    public_instance_methods(false).map do |method|
      method.to_s.match(/(.+)\?/) do |match|
        match[0].chop.to_sym
      end
    end.compact
  end

  def type
    Message.types.each do |type|
      method = (type.to_s << '?').to_sym
      return type if send(method)
    end
    :default
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

  attr_reader :message
end
