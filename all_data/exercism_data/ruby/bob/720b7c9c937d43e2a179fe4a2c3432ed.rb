class Bob
  def hey(message_content)
    message = ::Message.new(message_content)

    if message.nothing?
      'Fine. Be that way!'
    elsif message.shouting?
      'Woah, chill out!'
    elsif message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Message
  attr_reader :content

  def initialize(content)
    @content = content
  end

  # Can be whitespace
  def nothing?
    content.strip.empty?
  end

  # All uppercase
  def shouting?
    content.upcase == content && content.downcase != content
  end

  # It is only a question if the last character is a question mark
  def question?
    content.end_with? '?'
  end
end
