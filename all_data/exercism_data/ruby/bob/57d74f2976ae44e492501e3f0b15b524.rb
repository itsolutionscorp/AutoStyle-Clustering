class String
  def empty?
    return self =~ /\A\s*\z/
  end
end

class Bob
  def hey message
    if message.empty?
      return "Fine. Be that way!"
    end

    if screaming?(message)
      return "Woah, chill out!"
    end

    if question?(message)
      return "Whatever."
    else
      return "Sure."
    end
  end

  protected
  def question? message
    return message[-1] != ??
  end
  def screaming? message
    return message == message.upcase
  end
end
