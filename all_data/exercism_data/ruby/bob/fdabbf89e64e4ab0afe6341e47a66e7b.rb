class Bob
  def new
  end

  def hey command
    statement = Statement.new(command)

    case
    when statement.yelling? && statement.contains_words?
      "Woah, chill out!" 
    when statement.question?
      "Sure."
    when statement.blank?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end

class Statement < String
  def new value
  end

  def question?
    self[-1] === "?"
  end

  def yelling?
    self === self.upcase 
  end

  def contains_words?
    /([a-zA-Z]+\s*[a-zA-Z]*)+\w/ =~ self
  end

  def blank?
    self.strip.length === 0
  end
end
