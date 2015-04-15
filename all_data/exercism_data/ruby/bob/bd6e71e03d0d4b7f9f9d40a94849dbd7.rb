class Bob
  def hey(msg)
    if msg.strip == ""
      "Fine. Be that way!"
    elsif msg.match(/\p{Lower}/).nil? and msg =~ /[A-Z]/
      "Woah, chill out!"
    elsif msg =~ /\?\Z/
      "Sure."
    else
      "Whatever."
    end
  end
end
