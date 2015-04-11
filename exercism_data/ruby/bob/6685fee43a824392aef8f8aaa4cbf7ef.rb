# leave it here for practical use, should be a separate file
class Message < Struct.new(:text)
  def silent?
    text.nil? or text.empty?
  end

  def question?
    text.end_with? '?'
  end

  def shouted?
    text == text.upcase
  end
end

class Bob

  def hey(text)
    message = Message.new(text)
    return "Fine. Be that way." if message.silent?
    return "Woah, chill out!" if message.shouted?
    return "Sure." if message.question?
    "Whatever."
  end

end
