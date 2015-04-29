class Bob
  attr :empty_message_response, :shouty_message_response, :interrogative_message_response, :default_response

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

  def empty_message_response
    @empty_message_response || 'Fine. Be that way!'
  end

  def shouty_message_response
    @shouty_message_response || 'Woah, chill out!'
  end

  def interrogative_message_response
    @interrogative_message_response || 'Sure.'
  end

  def default_response
    @default_response || 'Whatever.'
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
