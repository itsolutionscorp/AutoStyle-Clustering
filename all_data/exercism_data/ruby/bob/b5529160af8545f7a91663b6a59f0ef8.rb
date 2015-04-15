class Bob
  def hey(msg)
    if msg =~ /[A-Z]{4,}/
      "Woah, chill out!"
    elsif msg[-1] == '?'
      "Sure."
    elsif msg =~ /[0-9]{1}/
      "Woah, chill out!"
    elsif msg =~ /(OK|DMV)/
      "Whatever."
    elsif msg.strip == ""
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
