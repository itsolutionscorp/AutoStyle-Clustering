class Bob
  def hey(msg)
    case
      when (msg.nil? or msg.empty?)
        "Fine. Be that way!"
      when (msg == msg.upcase)
        "Woah, chill out!"
      when (msg.end_with? "?")
        "Sure."
      else
        "Whatever."
    end
  end
end