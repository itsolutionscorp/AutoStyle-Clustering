class Bob  
  def hey(conversation)
    case
    when conversation.count(' ') == conversation.length
      return 'Fine. Be that way!'
    when conversation.scan(/[A-Z]/).length > 0 &&
        conversation == conversation.upcase
      return 'Woah, chill out!'
    when conversation.end_with?("?")
      return 'Sure.'
    else
      return 'Whatever.'
    end
  end
end
