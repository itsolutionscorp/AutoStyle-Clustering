class Bob
  def hey(msg)
    if msg =~ /\?\Z/
      "Sure."
    elsif msg.to_s.empty?
      "Fine. Be that way."
    elsif msg.upcase == msg
      "Woah, chill out!"
    else
      "Whatever."
    end
  end
end
