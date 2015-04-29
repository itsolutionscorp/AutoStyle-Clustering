class Bob

  def hey(statement)
    case statement
    when question
      'Sure.'
    when yell
      'Woah, chill out!'
    when talk
      "Whatever."
    else
      "Fine. Be that way!"
    end
  end

  private

  def question
    /^.+[a-z].+\?$/
  end

  def yell
    /^([^\sa-z]+\s?)+$/
  end

  def talk
    /^.*[a-z]+.*[^\?]$/
  end

end
