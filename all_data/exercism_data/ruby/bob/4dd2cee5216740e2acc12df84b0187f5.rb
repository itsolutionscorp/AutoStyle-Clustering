class Bob

  def hey(message)
    if message.nil? or message.empty?
      "Fine. Be that way."
    elsif message.end_with?("?")
      "Sure."
    elsif yelling?(message)
      "Woah, chill out!"
    else
      "Whatever." 
    end
  end


  private

  def yelling?(the_message)
    the_message == the_message.upcase
  end

end
