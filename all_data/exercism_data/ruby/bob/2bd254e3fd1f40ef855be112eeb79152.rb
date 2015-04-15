class Bob

  def hey(message_text)

    message = Message.new(message_text)

    if message.silence?
      'Fine. Be that way!'
    elsif message.shout?
      'Woah, chill out!'
    elsif message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Message

  def initialize(text)
    @text = text
  end

  def silence?
    @text.nil? || /^[ ]*$/ =~ @text
  end

  def shout?
    /^[^a-z]+$/ =~ @text
  end

  def question?
    /\?$/ =~ @text
  end
end
