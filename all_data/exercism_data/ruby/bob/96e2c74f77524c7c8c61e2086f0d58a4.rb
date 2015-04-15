class Bob

  def hey(message)

    last_message = find_last_message(message)

    if yelling?(last_message)
      "Whoa, chill out!"
    elsif question?(last_message)
      "Sure."
    elsif empty_message(last_message)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private

  def find_last_message(message)
    if message.match(/\n/)
      message.split("\n").last
    else
      message
    end
  end

  def empty_message(message)
    message.match(/^\s*$/m)
  end

  def yelling?(message)
    !has_lowercase_word?(message) && contains_uppercase_word?(message)
  end

  def contains_uppercase_word?(message)
    message.match(/[A-Z]+\b/)
  end

  def has_lowercase_word?(message)
    message.match(/[a-z]+\b/)
  end

  def question?(message)
    message.end_with? "?"
  end

end
