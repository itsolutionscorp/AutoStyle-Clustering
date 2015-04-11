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
  def question?
    self[-1] == "?"
  end

  def shouty?
    self == self.upcase
  end
end
