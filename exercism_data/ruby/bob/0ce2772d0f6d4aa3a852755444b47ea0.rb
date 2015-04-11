class Message
  def initialize(text)
    if text.kind_of?(String)
      text.strip!
      @silent = text.empty?
      @shouting = text.upcase == text
      @question = text.end_with?('?')
    end
  end

  def silent?
    @silent
  end

  def shouting?
    @shouting
  end

  def question?
    @question
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
