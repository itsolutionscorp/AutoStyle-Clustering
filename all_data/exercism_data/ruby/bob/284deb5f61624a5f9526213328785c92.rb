class Bob

  def hey(message)
    case
    when message.to_s.strip.empty?
     "Fine. Be that way!" 
    when !!(/^[^a-z]*$/ =~ message)
      "Woah, chill out!"
    when message[-1] == '?'
      "Sure."
    else 
      "Whatever."
    end
  end
end
