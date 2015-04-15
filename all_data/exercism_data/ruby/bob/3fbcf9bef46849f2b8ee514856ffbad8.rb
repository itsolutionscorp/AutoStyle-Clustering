class Bob

  def hey(conversation)
    if conversation.gsub(/\s/,"") == ""
      'Fine. Be that way!'
    elsif conversation.gsub(/(\W)|(\d)/,"") != "" && conversation.gsub(/(\W)|(\d)/,"") == conversation.gsub(/(\W)|(\d)/,"").upcase
      'Woah, chill out!'
    elsif conversation[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end

end
