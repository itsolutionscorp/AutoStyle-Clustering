class Bob

  def hey(words)

    return answers(:fine) if silence?(words)

    return answers(:shout) if shouting?(words)

    return answers(:question) if question?(words)

    answers(:default)
  end

  def shouting?(words)
    words == words.upcase
  end

  def question?(words)
    words.end_with?("?")
  end

  def silence?(words)
    words.empty?
  end

  def answers(command)
    { shout: "Woah, chill out!",
      question: "Sure.",
      fine: "Fine. Be that way.",
      default: "Whatever."
    }[command]
  end
end
