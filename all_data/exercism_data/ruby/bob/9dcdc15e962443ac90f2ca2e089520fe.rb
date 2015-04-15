class Bob
  def hey(message)
    has_letters = (message =~ /[A-Z]/)
    if has_letters &&  message.upcase == message
      return "Woah, chill out!"
    elsif message =~ /.+\?\Z/
      return "Sure."
    elsif message =~ /\A[ \t\n]*\Z/
      return "Fine. Be that way!"
    end
    return "Whatever."
  end
end
