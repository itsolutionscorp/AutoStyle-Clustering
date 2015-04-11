class Bob
  def hey words
    respond_to Phrase.new(words)
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

class Phrase
  def initialize words
    @words = words || ""
  end

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

  def words
    @words
  end
end
