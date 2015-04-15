class Message
  def initialize(text)
    if text.kind_of?(String)
      @text = text.strip
    else
      @text = 'error -- non-string input'
    end
  end

  def silent?
    @silent ||= @text.empty?
  end

  def shouting?
    @shouting ||= @text.upcase == @text
  end

  def question?
    @question ||= @text.end_with?('?')
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
