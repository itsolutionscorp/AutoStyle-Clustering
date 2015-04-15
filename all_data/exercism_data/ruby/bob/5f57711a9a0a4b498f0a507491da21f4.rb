class Bob

  def hey(message)
    case message
    when proc { |m| m == nil || m == "" } then "Fine. Be that way."
    when proc { |m| m.end_with?("?") } then "Sure."
    when message.upcase then "Woah, chill out!"
    else "Whatever."
    end
  end

end
