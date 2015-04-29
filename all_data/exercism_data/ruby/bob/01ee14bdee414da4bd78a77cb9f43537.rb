class Bob
  attr_accessor :empty_message_response, :shouty_message_response, :interrogative_message_response, :default_response

  def initialize(options = {})
    {
      empty_message_response: 'Fine. Be that way!',
      shouty_message_response: 'Woah, chill out!',
      interrogative_message_response: 'Sure.',
      default_response: 'Whatever.'
    }.merge(options).each {|key, value| self.send("#{key}=", value)}
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
