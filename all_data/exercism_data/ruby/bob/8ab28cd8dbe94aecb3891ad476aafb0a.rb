class Bob
  def hey(message)
    message ||= ''
    case true
    when message.empty? then "Fine. Be that way."
    when message == message.upcase then "Woah, chill out!"
    when message[-1] == '?' then "Sure."
    else "Whatever."
    end
  end
end
