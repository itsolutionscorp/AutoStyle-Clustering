class Bob

  def hey(phrase)
    @phrase = phrase.gsub(/[0-9\t ]/, '')
    return RESPONSES[:nothing] if nothing?
    return RESPONSES[:yell] if yell?
    return RESPONSES[:question] if question?
    RESPONSES[:other]
  end

  private

  def nothing?
    @phrase.nil? || @phrase.empty?
  end

  def question?
    @phrase[-1] == "?"
  end

  def yell?
    letters == letters.upcase unless letters.nil?
  end

  def letters
    @phrase[/[a-zA-Z]+/]
  end

  RESPONSES = {
    nothing: "Fine. Be that way!",
    yell: "Whoa, chill out!",
    question: "Sure.",
    other: "Whatever."
  }

end
