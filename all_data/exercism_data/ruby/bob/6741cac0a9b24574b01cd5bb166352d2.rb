class Bob
  def hey(conversation)
    case conversation.strip
    when ''
      'Fine. Be that way!'
    when conversation.upcase
      'Woah, chill out!'
    when /\?\z/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
