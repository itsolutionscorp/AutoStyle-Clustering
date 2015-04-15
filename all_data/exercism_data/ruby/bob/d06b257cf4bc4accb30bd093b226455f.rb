class Statement

  def initialize(text)
    @text = text
  end

  def question?
    @text.end_with? "?"
  end

  def shouting?
    has_words? && all_uppercase?
  end

  def has_words?
    @text.match(/[a-zA-Z]/)
  end

  def all_uppercase?
    @text.upcase == @text
  end

  def nothing?
    @text.strip == ""
  end

end

class Bob

  def hey(text)
    statement = Statement.new(text)

    case
    when statement.shouting?
      "Woah, chill out!"
    when statement.question?
      "Sure."
    when statement.nothing?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

end
