class Bob

  def hey(input)
    if are_you_shouting_at_me?(input)
      return 'Woah, chill out!'
    end
    if any_questions?(input)
      return "Sure."
    end
    if (you_said_something?(input))
      return "Whatever."
    end
    return 'Fine. Be that way!'
  end

private
  def are_you_shouting_at_me?(str)
    return ((str.upcase == str) and (str.match(/[A-Z]/)))
  end

  def any_questions?(str)
    return str.end_with?('?')
  end

  def you_said_something?(str)
    return str.match(/\S/)
  end

end
