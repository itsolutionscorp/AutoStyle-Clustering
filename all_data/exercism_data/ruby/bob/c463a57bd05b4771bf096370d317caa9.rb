class Bob
  def hey(conversation)
    return "Fine. Be that way." if conversation.nil? || conversation == ''
    return "Sure."              if conversation =~ /\?\z/
    return "Woah, chill out!"   if conversation == conversation.upcase
    return "Whatever."
  end
end
