class Bob

  def hey (conversation)
    conversation.replace(remove_new_line_characters(conversation))

    if conversation_is_empty(conversation)
      'Fine. Be that way!'
    elsif conversation_all_caps(conversation)
      'Woah, chill out!'
    elsif conversation_is_a_question(conversation)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def remove_new_line_characters(string)
    string.gsub("\n","")
  end

  def conversation_is_empty(string)
    string.strip.empty?
  end

  def conversation_all_caps(string)
    string =~ /[[:alpha:]]/ && string == string.upcase
  end

  def conversation_is_a_question(string)
    string =~ /(.+\?$)/
  end

end
