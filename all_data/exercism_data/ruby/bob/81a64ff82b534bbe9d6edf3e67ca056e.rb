class Bob
  def hey(message_text)
    message = Message.new(message_text)
    if message.empty?
      "Fine. Be that way!"
    elsif message.upper?
      'Woah, chill out!'
    elsif message.ask?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Message
  def initialize(text)
    @text = text ? text.strip : ''
  end

  def empty?
    @text.empty?
  end

  def upper?
    @text.index(/[a-z]/).nil?
  end

  def ask?
    @text.end_with?('?')
  end
end
