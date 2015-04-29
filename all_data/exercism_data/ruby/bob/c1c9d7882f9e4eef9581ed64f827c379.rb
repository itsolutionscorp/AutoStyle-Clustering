class Bob
  def hey(message)
    response = "Sure."              if message.end_with?("?")
    response = "Woah, chill out!"   if message == message.upcase && (/[a-zA-Z]/).match(message)
    response = "Fine. Be that way!" if message.strip == ""
    response = "Whatever."          if response == nil
    return response
  end
end
