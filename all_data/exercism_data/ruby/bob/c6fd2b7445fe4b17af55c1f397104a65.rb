class Bob

  def hey(message)
    msg = Message.new(message)

    if msg.is_empty?
      "Fine. Be that way!"
    elsif msg.is_yelling?
      "Woah, chill out!"
    elsif msg.is_question?
      "Sure."
    else
      "Whatever."
    end

  end

end

class Message

  attr_reader :text

  def initialize(text)
    @text = text.to_s
  end

  def is_question?
    text[-1] == '?'
  end

  def is_yelling?
    text == text.upcase
  end

  def is_empty?
    text.empty?
  end

end
