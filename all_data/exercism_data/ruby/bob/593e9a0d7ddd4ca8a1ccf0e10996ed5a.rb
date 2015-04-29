class Bob
  def hey(message)
    case message.to_s
    when silence?
      "Fine. Be that way!"
    when shout?
      "Woah, chill out!"
    when question?
      "Sure."
    else
      "Whatever."
    end
  end

  private
  def silence?
    /^\s*$/
  end

  def shout?
    /^[^a-z]*$/
  end

  def question?
    /\?$/
  end
end
