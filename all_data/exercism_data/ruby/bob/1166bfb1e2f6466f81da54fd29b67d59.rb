class Bob

  def hey(message)
    if message == message.upcase && message.include?("!")
      "Woah, chill out!"
    elsif message.include?("?")
      "Sure."
    elsif message.empty?
      "Fine, be that way."
    else
      "Whatever."
    end
  end

end
