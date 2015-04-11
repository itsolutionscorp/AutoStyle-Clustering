class Bob
  def hey(message_to_bob)
    teen_interpreted_message = interpret_message(message_to_bob)
    if teen_interpreted_message.silent?
      'Fine. Be that way!'
    elsif teen_interpreted_message.yelling?
      'Woah, chill out!'
    elsif teen_interpreted_message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  def interpret_message(message_string)
    MessageToATeen.new(message_string)
  end
end

class MessageToATeen
  def initialize(message_text)
    @message_text = message_text
  end

  def silent?
    @message_text.strip.empty?
  end

  def yelling?
    @message_text.match(/[a-zA-Z]+/) && @message_text.upcase == @message_text
  end

  def question?
    @message_text[-1] == '?'
  end
end
