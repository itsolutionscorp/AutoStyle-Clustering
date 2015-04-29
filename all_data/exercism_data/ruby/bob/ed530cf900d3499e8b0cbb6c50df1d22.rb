class Bob
  def hey(msg)
    case msg
    when /[a-z]/
      msg.end_with?(??) ? "Sure." : "Whatever."
    when /[A-Z]/
      "Woah, chill out!"
    else
      "Fine. Be that way!"
    end
  end
end
