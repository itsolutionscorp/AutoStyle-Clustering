class Bob
  RESPONSES = {
    relax: "Woah, chill out!",
    uncaring: "Whatever.",
    question: "Sure.",
    silence: "Fine. Be that way."
  }

  def hey(statement)
    return RESPONSES[:silence] unless present?(statement)
    if shouting?(statement)
      RESPONSES[:relax]
    elsif question?(statement)
      RESPONSES[:question]
    else
      RESPONSES[:uncaring]
    end
  end

  private
  def shouting?(statement)
    statement.upcase == statement
  end

  def question?(statement)
    statement[-1] == '?'
  end

  def present?(statement)
    statement && statement.length > 0
  end

end
