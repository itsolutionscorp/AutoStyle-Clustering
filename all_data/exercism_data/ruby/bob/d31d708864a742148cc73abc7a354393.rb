class Bob
  def hey(message)
    case message.to_s
    when silence?
      "Fine. Be that way!"
    when shout?
      "Woah, chill out!"
    when question?
      "Sure."
    when statement?, command?
      "Whatever."
    end
  end

  private
  def silence?
    /^\s*$/
  end

  def statement?
    /\.$/
  end

  def shout?
    /^[^a-z]*$/
  end

  def question?
    /\?$/
  end

  def command?
    /[A-Z][a-z].*?!$/
  end
end
