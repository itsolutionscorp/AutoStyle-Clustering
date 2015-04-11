class Bob

  def hey(message)
    return 'Woah, chill out!' if message !~ /[a-z]/ && message =~ /[A-Z]/
    return "Sure." if message[-1] == '?'
    return 'Fine. Be that way!' if !(message =~ /\S/)
    "Whatever."
  end

end
