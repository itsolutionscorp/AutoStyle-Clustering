class Bob
  def hey(message)
    if yelling?(message)
      "Woah, chill out!"
    elsif asking?(message)
      "Sure."
    elsif silent?(message)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private

  def yelling?(message)
    message =~ /[A-Z]/ && message !~ /[a-z]/
  end

  def asking?(message)
    message.end_with?('?')
  end

  def silent?(message)
    message.strip.empty?
  end
end
