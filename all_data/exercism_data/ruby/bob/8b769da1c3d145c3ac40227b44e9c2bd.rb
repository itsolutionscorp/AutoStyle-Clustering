class Bob
  def hey(statement)
    formatted = format(statement)
    return "Woah, chill out!" if is_an_exclamation?(formatted)
    return "Sure." if is_a_question?(formatted)
    return "Fine. Be that way!" if is_silence?(formatted)
    return "Whatever."
  end

private
  def format(statement)
    statement.gsub("\n", " ")
  end

  def is_a_question?(statement)
    statement.match(/\?$/)
  end

  def is_an_exclamation?(statement)
    (statement.upcase == statement) && (statement.match(/[a-zA-z]/))
  end

  def is_silence?(statement)
    only_whitespace = /^\s*$/
    statement.match(only_whitespace)
  end
end
