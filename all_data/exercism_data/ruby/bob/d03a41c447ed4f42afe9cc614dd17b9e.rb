class Message
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def letters?
    phrase =~ /[A-Z|a-z]/
  end

  def shouting?
    phrase.upcase == phrase if letters?
  end

  def question?
    phrase.end_with?("?")
  end

  def silence?
    phrase.strip.empty?
  end
end

class Bob

  def hey(phrase)
    message = Message.new(phrase)
    case 
    when message.silence?
      "Fine. Be that way!"
    when message.shouting?
      "Woah, chill out!"
    when message.question?
      "Sure."
    else
      "Whatever."
    end
  end
end
