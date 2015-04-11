class Bob

  def hey(words)

    return answers(:fine) if silence?(words)

    return answers(:shout) if all_caps?(words)

    return answers(:question) if question?(words)

    answers(:default)
  end

  def all_caps?(words)
    words == words.upcase
  end

  def question?(words)
    "?" == words.split("").last
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
