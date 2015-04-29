class Bob
  def hey(words)
    message = Message.new(words)
    if message.empty?
      "Fine. Be that way."
    elsif message.shouty?
      "Woah, chill out!"
    elsif message.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Message < String
  def initialize(string)
    super(string.to_s)
  end

  def question?
    self.end_with? "?"
  end

  def shouty?
    self == self.upcase
  end
end
