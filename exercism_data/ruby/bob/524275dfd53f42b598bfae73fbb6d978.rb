class Bob

  def hey(text)
    response_for Message.new(text)
  end

  private

  def response_for(message)
    if message.silent?
      "Fine. Be that way!"
    elsif message.yelling?
      "Whoa, chill out!"
    elsif message.question?
      "Sure."
    else
      "Whatever."
    end
  end

end

class Message
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def question?
    text.end_with?("?")
  end

  def yelling?
    text[/[a-z]/i] && text == text.upcase
  end

  def silent?
    text.strip.empty?
  end

end
