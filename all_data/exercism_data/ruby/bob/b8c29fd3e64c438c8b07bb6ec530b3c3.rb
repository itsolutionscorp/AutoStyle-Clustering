class Bob
  def hey text
    statement = Statement.new(text)
    case 
    when statement.silence?
      "Fine. Be that way."
    when statement.shouting?
      "Woah, chill out!"
    when statement.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Statement
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase.to_s
  end

  def silence?
    phrase.empty?
  end

  def question?
    phrase.end_with? "?"
  end

  def shouting?
    phrase.upcase == phrase
  end
end
