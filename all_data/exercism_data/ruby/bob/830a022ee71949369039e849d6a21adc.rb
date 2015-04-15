class Bob

  def hey(msg)
    answer(Message.new(msg))
  end

  def answer(message)
    return fine      if message.silent?
    return chill_out if message.shout?
    return sure      if message.question?
    return whatever
  end

  def whatever
    "Whatever."
  end

  def sure
    "Sure."
  end

  def chill_out
    "Woah, chill out!"
  end

  def fine
    "Fine. Be that way!"
  end
end

class Message
  def initialize(content)
    @content = content
  end

  def question?
    @content.end_with?("?")
  end

  def shout?
    @content == @content.upcase
  end

  def silent?
    @content.nil? or @content.strip.empty?
  end
end
