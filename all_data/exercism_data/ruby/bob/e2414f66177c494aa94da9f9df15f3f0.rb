class Bob
  def hey(msg)
    if msg.nil? || msg.strip.empty?
      "Fine. Be that way!"
    elsif msg == msg.upcase
      "Woah, chill out!"
    elsif msg =~ /\?$/
      "Sure."
    else
      "Whatever."
    end
  end
end
