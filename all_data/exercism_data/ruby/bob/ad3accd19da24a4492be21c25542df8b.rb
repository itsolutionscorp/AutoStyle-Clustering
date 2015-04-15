class Bob

  def hey(input_message)
    case 
    when input_message.end_with? "?"
      "Sure."
    when input_message.upcase == input_message
      "Woah, chill out!"
    when input_message.strip.empty?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

end
