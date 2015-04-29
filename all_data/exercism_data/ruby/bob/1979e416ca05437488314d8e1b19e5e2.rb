class Bob

  def hey msg
    msg = msg.split("\n").join("")
    if is_silence?(msg)
      "Fine. Be that way!"
    elsif is_yellin?(msg)
      "Woah, chill out!"
    elsif is_question?(msg)
      "Sure."
    else
      "Whatever."
    end
  end

private

  def is_yellin?(text)
    text == text.upcase
  end

  def is_question?(text)
    text =~ /\?$/
  end

  def is_silence?(text)
    text =~ /^\s*$/
  end

end
