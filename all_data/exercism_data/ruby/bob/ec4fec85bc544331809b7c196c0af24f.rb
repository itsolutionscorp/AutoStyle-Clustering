class Bob
  def hey(msg)
    if msg.to_s.empty?
      "Fine. Be that way."
    elsif msg.upcase == msg
      "Woah, chill out!"
    elsif msg.chars.last == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end
