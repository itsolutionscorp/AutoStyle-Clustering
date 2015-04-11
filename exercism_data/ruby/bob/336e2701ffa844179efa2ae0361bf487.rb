class Bob

  def hey(content)
    message = Message.new(content)
    case
    when message.blank?
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

class Message
  attr_accessor :content

  def initialize(content)
    @content = content
  end

  def blank?
    /\A\s*\z/ === content
  end

  def shouting?
    /\A[A-Z\W\d]+\z/ === content && /[A-Z]/ === content
  end

  def question?
    /\?\z/ === content
  end

end
