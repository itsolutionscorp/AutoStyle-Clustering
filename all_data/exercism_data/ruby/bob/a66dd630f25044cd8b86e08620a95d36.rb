class Bob

  def hey(expression)
    phrase = Phrase.new(expression)

    case
    when phrase.silent? then "Fine. Be that way!"
    when phrase.yelling? then "Woah, chill out!"
    when phrase.question? then "Sure."
    else "Whatever."
    end
  end

end

class Phrase

  def initialize phrase
    @phrase = phrase
  end

  def yelling?
    !silent? && @phrase == @phrase.upcase
  end

  def question?
    @phrase.end_with? "?"
  end

  def silent?
    @phrase.strip.empty?
  end

end
