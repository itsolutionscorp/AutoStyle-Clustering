class Bob

  def hey(words)
    phrase = Phrase.new words

    if phrase.silence?
      "Fine. Be that way!"
    elsif phrase.question?
      "Sure."
    elsif phrase.shout?
      "Woah, chill out!"
    else
      "Whatever."
    end
  end

end


class Phrase

  attr_reader :words

  def initialize(words)
    @words = words
  end

  def silence?
    words.strip.empty?
  end

  def question?
    !shout? && words.end_with?("?")
  end

  def shout?
    !silence? && words.upcase == words
  end

end
