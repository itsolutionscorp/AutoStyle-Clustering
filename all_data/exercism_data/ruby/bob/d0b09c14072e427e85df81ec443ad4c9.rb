class Bob
  def hey(words)
    if nothing?(words)
      "Fine. Be that way."
    elsif shouting?(words)
      "Woah, chill out!"
    elsif question?(words)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def nothing?(words)
    words.nil? || words.empty?
  end

  def shouting?(words)
    words == words.upcase
  end

  def question?(words)
    words.end_with?("?")
  end
end
