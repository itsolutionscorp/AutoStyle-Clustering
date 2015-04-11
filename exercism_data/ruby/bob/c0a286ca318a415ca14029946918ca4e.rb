class Bob
  def hey(message)
    if no_comment?(message)
      "Fine. Be that way!"
    elsif yelling?(message)
      "Woah, chill out!"
    elsif question?(message)
      "Sure."
    else
      "Whatever."
    end
  end
end

def no_comment?(message)
  message.strip.empty?
end

def yelling?(message)
  message.match(/\p{Upper}/) && !message.match(/\p{Lower}/)
end

def question?(message)
  message[-1] == '?'
end
