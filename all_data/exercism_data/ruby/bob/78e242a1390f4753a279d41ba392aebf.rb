class Bob
  attr_accessor :empty_message_response, :shouty_message_response, :interrogative_message_response, :default_response

  def initialize(options = {})
    self.empty_message_response = options.fetch(:empty_message_response, 'Fine. Be that way!')
    self.shouty_message_response = options.fetch(:shouty_message_response, 'Woah, chill out!')
    self.interrogative_message_response = options.fetch(:interrogative_message_response, 'Sure.')
    self.default_response = options.fetch(:default_response, 'Whatever.')
  end

  def hey(msg)
    msg = Message.new(msg)
    if msg.empty?
      empty_message_response
    elsif msg.shouty?
      shouty_message_response
    elsif msg.interrogative?
      interrogative_message_response
    else
      default_response
    end
  end
end

class Message
  attr :content

  def initialize(content)
    @content = content.to_s
  end

  def empty?
    content.strip.empty?
  end

  def shouty?
    content == content.upcase
  end

  def interrogative?
    content.end_with?('?')
  end
end
