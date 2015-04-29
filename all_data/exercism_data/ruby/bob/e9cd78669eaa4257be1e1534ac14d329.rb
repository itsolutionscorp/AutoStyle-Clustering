class Bob
  def hey(msg)
    if msg =~ /\A\s*\Z$/
      "Fine. Be that way!"
    elsif msg.upcase == msg
      "Woah, chill out!"
    elsif msg[-1] == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end
