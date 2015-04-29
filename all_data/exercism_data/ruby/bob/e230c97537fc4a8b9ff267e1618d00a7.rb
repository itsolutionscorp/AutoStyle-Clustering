class Bob
  def hey(phrase)
    phrase = Message.new(phrase)
    if phrase.shouting?
      "Woah, chill out!"
    elsif phrase.question?
      "Sure."
    elsif phrase.silence?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end

class Message
  def initialize(phrase)
    @phrase = phrase
  end

  def shouting?
    @phrase == @phrase.upcase && @phrase.match(/[a-z]/i)
  end

  def question?
    @phrase.end_with?('?')
  end

  def silence?
    @phrase.match(/\A\s*\z/)
  end
end
