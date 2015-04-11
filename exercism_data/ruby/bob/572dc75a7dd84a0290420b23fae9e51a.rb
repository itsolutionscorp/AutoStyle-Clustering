class Bob
  def hey(phrase)
    return "Fine. Be that way!" if phrase.strip.empty?

    is_question = (phrase.chars[-1] == "?")
    is_forceful = !(phrase.match(/[a-z]+/))

    if is_forceful
      "Woah, chill out!"
    elsif is_question
      "Sure."
    else
      "Whatever."
    end
  end
end
