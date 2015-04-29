class Bob
  def hey(phrase)
    return "Fine. Be that way!" if phrase.strip.empty?

    is_question = phrase.end_with?("?")
    is_forceful = phrase.upcase == phrase

    if is_forceful
      "Woah, chill out!"
    elsif is_question
      "Sure."
    else
      "Whatever."
    end
  end
end
