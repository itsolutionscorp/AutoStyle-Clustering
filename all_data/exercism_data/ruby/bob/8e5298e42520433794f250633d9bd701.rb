class Bob

  def hey(input_message)

    input_message ||= ""
    return_value = "Whatever."
    return_value = "Sure."              if input_message[-1,1]=="?"
    return_value = "Woah, chill out!"   if input_message.upcase == input_message
    return_value = "Fine. Be that way!" if input_message.strip == ""
    return_value
  end

end
