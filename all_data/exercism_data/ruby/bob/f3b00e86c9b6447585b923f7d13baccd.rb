class Bob
  def hey(msg)
    msg.strip!
    case msg
    when /\?$/
      "Sure"
    when /^[A-Z]+$/
      "Woah, chill out!"
    when /^$/
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
