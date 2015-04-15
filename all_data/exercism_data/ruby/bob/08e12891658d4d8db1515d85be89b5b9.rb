class Bob

  def hey(word)
    return "Fine. Be that way!" if word.nil? or word === ""
    return "Woah, chill out!" if word.upcase === word
    return "Sure." if word[-1, 1] === "?"
    "Whatever."
  end
end
