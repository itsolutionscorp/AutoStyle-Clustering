class Message
  def initialize(text)
    text.strip!
    @text = text
  end

  def silent?
    @text.empty?
  end

  def shouting?
    @text.upcase == @text
  end

  def question?
    @text.end_with?('?')
  end
end

class Bob
  SILENCE_RESPONSE = 'Fine. Be that way!'
  SHOUTING_RESPONSE = 'Woah, chill out!'
  QUESTION_RESPONSE = 'Sure.'
  GENERIC_RESPONSE = 'Whatever.'

  def hey(text)
    message = Message.new(text)

    if message.silent?
      return SILENCE_RESPONSE
    elsif message.shouting?
      return SHOUTING_RESPONSE
    elsif message.question?
      return QUESTION_RESPONSE
    else
      return GENERIC_RESPONSE
    end
  end

end
