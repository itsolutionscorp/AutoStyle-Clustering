class Bob
  def hey words
    words = words.strip.gsub(/\s/, "")

    if words.empty?
      "Fine. Be that way!"
    elsif is_shouting? words
      "Woah, chill out!"
    elsif is_question? words
      "Sure."
    else
      "Whatever."
    end
  end

  private
  def is_shouting?(words)
    (words == words.upcase)
  end

  def is_question?(words)
    (words.end_with? "?")
  end
end
