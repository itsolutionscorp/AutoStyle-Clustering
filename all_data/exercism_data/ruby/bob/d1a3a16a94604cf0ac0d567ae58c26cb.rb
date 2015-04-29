class Bob
  def hey(message)
    if message.nil? || message==''
      'Fine. Be that way.'
    elsif message.upcase == message
      "Woah, chill out!"
    elsif message =~ /\?$/
      "Sure."
    else
      "Whatever."
    end
  end
end
