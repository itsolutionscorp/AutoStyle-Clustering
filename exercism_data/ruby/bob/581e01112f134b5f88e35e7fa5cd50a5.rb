class Bob

  def hey command
    statement = Statement.new(command)

    case
    when statement.yelling?
      "Woah, chill out!" 
    when statement.question?
      "Sure."
    when statement.silence?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end

class Statement < String
  def question?
    self[-1] === "?"
  end

  def yelling?
    self === self.upcase && self.contains_words?
  end

  def silence?
    self.strip.length === 0
  end

  private_class_method
  def contains_words?
    /([a-zA-Z]+\s*[a-zA-Z]*)+\w/ =~ self
  end
end
