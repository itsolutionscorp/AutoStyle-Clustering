class Bob
  def hey(statement)
    phrase = Phrase.new(statement)

    if phrase.blank?
      "Fine. Be that way."
    elsif phrase.uppercase?
      "Woah, chill out!"
    elsif phrase.question?
      "Sure."
    else
      "Whatever."
    end
  end

end

class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def uppercase?
    @phrase == @phrase.upcase
  end

  def question?
    @phrase.end_with?("?")
  end

  def blank?
    !@phrase || @phrase.length == 0
  end
end
