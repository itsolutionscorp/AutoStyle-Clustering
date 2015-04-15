class Bob

  def hey(received_message)
    message = Message.new(received_message)

    if message.silent?
      "Fine. Be that way!"
    elsif message.shouting?
      "Woah, chill out!"
    elsif message.question?
      "Sure."
    else
      "Whatever."
    end
  end

end

class Message

  def initialize(phrase)
    @phrase = phrase
  end

  def silent?
    @phrase.strip.empty?
  end

  def shouting?
    @phrase.upcase == @phrase
  end

  def question?
    @phrase.end_with?("?")
  end

end
