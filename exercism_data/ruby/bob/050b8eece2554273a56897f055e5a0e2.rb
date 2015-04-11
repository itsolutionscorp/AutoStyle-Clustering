class Bob
  def hey(conversation)
    if conversation.end_with?('?') and conversation != conversation.upcase
      "Sure."
    elsif conversation.strip.length == 0
      "Fine. Be that way!"
    elsif conversation.upcase == conversation 
      "Woah, chill out!"
    else
      "Whatever."
    end
  end 
end
