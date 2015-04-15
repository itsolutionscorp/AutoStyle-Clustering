class Bob

  def hey(input)
    if is_anyone_shouting?(input)
      return 'Woah, chill out!'
    end
    if any_questions?(input)
      return "Sure."
    end
    if not (you_said_something?(input))
      return 'Fine. Be that way!'
    end
    return "Whatever."
  end

private
  def is_anyone_shouting?(str)
    (str.upcase == str) and (str.match(/[A-Z]/)
  end

  def any_questions?(str)
    str.end_with?('?')
  end

  def you_said_anything?(str)
    str.match(/\S/)
  end

end
