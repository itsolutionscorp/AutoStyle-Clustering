class Bob
  def hey(expression)
    return "Fine. Be that way!" if silent? expression
    return "Woah, chill out!" if shouting? expression
    return "Sure." if question? expression
    "Whatever."
  end

  def silent?(expression)
    expression.strip.empty?
  end

  def shouting?(expression)
    !expression[/[[:lower:]]/] and expression[/[[:upper:]]/]
  end

  def question?(expression)
    expression.end_with? '?'
  end
end
