class Bob
  def hey(message)
    if empty?(message)
      "Fine. Be that way!"
    elsif yelling?(message)
      "Woah, chill out!"
    elsif question?(message)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def empty?(message)
    message =~ /\A\s*\z/
  end

  def yelling?(message)
    message !~ /[a-z]/ && message =~ /[A-Z]/
  end

  def question?(message)
    message =~ /\?\z/
  end
end
