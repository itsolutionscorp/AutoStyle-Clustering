class Bob
  def hey(string)
    message = Message.new(string.to_s)

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

class Message < String
  def silent?
    strip.empty?
  end

  def question?
    end_with? "?"
  end

  def shouting?
    upcase == self
  end
end
