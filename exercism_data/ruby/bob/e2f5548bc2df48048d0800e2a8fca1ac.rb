class Bob
  def hey(msg)
    if msg.nil? || msg.empty?
      "Fine. Be that way."
    elsif msg.upcase == msg
      "Woah, chill out!"
    elsif msg.end_with?("?")
      "Sure."
    elsif msg.end_with?("!")
      "Whatever."  
    else
      "Whatever."
    end
  end
end
