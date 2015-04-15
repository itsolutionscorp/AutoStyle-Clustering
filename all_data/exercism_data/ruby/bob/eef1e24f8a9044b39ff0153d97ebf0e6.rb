class Bob
  def hey(words)
    message = Message.new(words)

    if message.shouting?
      chill_out
    elsif message.question?
      sure
    elsif message.empty?
      annoyed
    else
      whatever
    end
  end

  private

  def annoyed
    "Fine. Be that way!"
  end

  def whatever
    "Whatever."
  end

  def chill_out
    "Woah, chill out!"
  end

  def sure
    "Sure."
  end
end

class Message
  attr_accessor :message

  def initialize(message)
    self.message = message
  end

  def shouting?
    !message.match(/[a-z]/) && message.match(/[A-Z]/)
  end

  def question?
    message.end_with? '?'
  end

  def empty?
    message.strip.empty?
  end
end
