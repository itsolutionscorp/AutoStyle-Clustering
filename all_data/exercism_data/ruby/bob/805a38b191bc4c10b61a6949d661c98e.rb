class Bob

  def hey(expression)
    phrase = Phrase.new(expression)
    case
    when phrase.silent?
      "Fine. Be that way!"
    when phrase.yelling?
      "Woah, chill out!"
    when phrase.question?
      "Sure."
    else
      "Whatever."
    end
  end

end

class Phrase

  def initialize phrase
    @phrase = phrase
  end

  def yelling?
    not silent? and @phrase == @phrase.upcase
  end

  def question?
    @phrase.end_with? "?"
  end

  def silent?
    @phrase.strip.empty?
  end

end
