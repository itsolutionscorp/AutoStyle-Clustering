class Bob
  def hey(msg)
    if msg.nil? || msg.length < 1
      "Fine. Be that way."
    elsif msg == msg.upcase
      "Woah, chill out!"
    elsif msg =~ /[?]\Z/
      "Sure."
    else
      "Whatever."
    end
  end
end
