class Bob
  def hey words
    respond_to Phrase.new(words.to_s)
  end

  private

  def respond_to phrase
    if phrase.silence?
      "Fine. Be that way!"
    elsif phrase.shout?
      "Woah, chill out!"
    elsif phrase.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

Phrase = Struct.new(:words) do
  def silence?
    words.empty?
  end

  def shout?
    !silence? && only_uppercase?
  end

  def question?
    words.end_with? "?"
  end

  private

  def only_uppercase?
    words == words.upcase
  end
end
