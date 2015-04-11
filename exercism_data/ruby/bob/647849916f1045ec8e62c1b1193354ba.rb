class Bob
  def hey(msg)
    message = msg.to_s
    if message.upcase === message && message.length > 0
      "Woah, chill out!"
    elsif message =~ /\?$/
      "Sure."
    elsif message.length == 0
      "Fine. Be that way."
    else
      "Whatever."
    end
  end
end
