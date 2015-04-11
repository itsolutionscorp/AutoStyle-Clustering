class Bob

  def hey(input_message)
    input_message ||= ""
    return_value = "Whatever."
    case
    when input_message.strip.empty?
      return_value = "Fine. Be that way!"
    when input_message.upcase == input_message
      return_value = "Woah, chill out!"
    when input_message.end_with?("?")
      return_value = "Sure."    
    end
    return_value
  end
end
