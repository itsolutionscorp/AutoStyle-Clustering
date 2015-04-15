class Bob
  def hey(message)
    if message == message.upcase && /[a-zA-Z]/.match(message)
      "Woah, chill out!"
    elsif message[-1,1] == "?"
      "Sure."
    elsif message.strip == ""
      "Fine. Be that way!"
    else
      "Whatever."
    end    
  end
end
