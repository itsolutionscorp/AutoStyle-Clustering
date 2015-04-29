class Bob

  def hey(message)
    respond_with(MessageParser.new(message))
  end

  private

  def respond_with(message)
    if message.upcased? && message.has_characters?
      'Woah, chill out!'
    elsif message.last_character_is_question_mark?
      'Sure.'
    elsif message.empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class MessageParser
  def initialize(body)
    @body = body
  end

  def upcased?
    @body == @body.upcase
  end

  def has_characters?
    @body =~ /[a-z]/i
  end

  def last_character_is_question_mark?
    @body[-1,1] == '?'
  end

  def empty?
    @body.strip.empty?
  end
end
