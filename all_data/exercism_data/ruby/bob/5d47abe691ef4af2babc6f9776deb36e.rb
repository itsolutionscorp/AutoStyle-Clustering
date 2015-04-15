class Bob
  def hey(message)
    if silence?(message)
      "Fine. Be that way!"
    elsif shouting?(message)
      "Woah, chill out!"
    elsif question?(message)
      "Sure."
    else
      "Whatever."
    end
  end

  private
  def silence?(message)
    message.nil? || message.empty? || message =~ /\A\s+\z/
  end

  def question?(message)
    message =~ /\?\z/
  end

  def shouting?(message)
    message !~ /[a-z]/
  end
end
