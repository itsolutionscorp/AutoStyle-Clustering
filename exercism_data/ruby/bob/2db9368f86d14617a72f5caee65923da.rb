class Bob
  def hey(text)
    text = text.to_s

    return "Sure."              if question?(text)
    return 'Fine. Be that way.' if silence?(text)
    return "Woah, chill out!"   if all_caps?(text)
    return "Whatever."
  end

  private

  def all_caps?(text)
    !text.match(/[a-z]/)
  end

  def question?(text)
    text.match(/\?$/)
  end

  def silence?(text)
    text == ""
  end
end
