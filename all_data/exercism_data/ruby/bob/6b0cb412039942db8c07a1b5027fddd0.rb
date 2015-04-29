# leave it here for practical use, should be a separate file
class Message < Struct.new(:text)
  def is_silent?
    text.nil? or text.empty?
  end

  def is_a_question?
    text.end_with? '?'
  end

  def is_shouted?
    text == text.upcase
  end
end

class Bob

  def hey(text)
    message = Message.new(text)
    return "Fine. Be that way." if message.is_silent?
    return "Woah, chill out!" if message.is_shouted?
    return "Sure." if message.is_a_question?
    "Whatever."
  end

end
