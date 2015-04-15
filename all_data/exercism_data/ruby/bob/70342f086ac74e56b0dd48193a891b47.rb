class Bob
  def hey(words)
    return "Woah, chill out!" if shout?(words)
    return "Sure." if question?(words)
    return "Fine. Be that way!" if silence?(words)
    "Whatever."
  end

  private

  def silence?(words)
    words.strip == ''
  end

  def question?(words)
    words[-1] == "?"
  end

  def shout?(words)
    words == words.upcase && words != words.downcase
  end
end
