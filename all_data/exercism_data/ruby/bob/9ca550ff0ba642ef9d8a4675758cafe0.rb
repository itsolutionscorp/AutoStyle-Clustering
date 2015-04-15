class Bob
  def hey(msg)
    # A blank/empty/nil message
    if msg.to_s.empty?
      "Fine. Be that way."
    # A SHOUTY UPPERCASE MESSAGE
    elsif msg.upcase == msg
      "Woah, chill out!"
    # A question ending in ?
    elsif msg.chars.last == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end
