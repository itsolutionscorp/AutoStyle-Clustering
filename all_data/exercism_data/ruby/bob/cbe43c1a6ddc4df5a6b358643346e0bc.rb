class Bob
  def hey *args
    message = args[0]
    if message =~ /\A\s*\z/
      return "Fine. Be that way!"
    end

    if screaming_at_me?(message)
      return "Woah, chill out!"
    end

    case message[-1]
      when ?!
        return "Whatever."
      when ??
        return "Sure."
      else
        return "Whatever."
    end
  end

  protected
  def screaming_at_me? message
    return message[0..2] == message[0..2].upcase
  end
end
