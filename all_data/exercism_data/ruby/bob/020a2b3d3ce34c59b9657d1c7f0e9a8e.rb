class Bob

  def hey(message)
    if upcased_message?(message) && message_with_characters?(message)
      'Woah, chill out!'
    elsif last_character_is_question_mark?(message)
      'Sure.'
    elsif message_is_empty_with_stripped_whitespace?(message)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private

  def upcased_message?(msg)
    msg == msg.upcase
  end

  def message_with_characters?(msg)
    msg =~ /[a-z]/i
  end

  def last_character_is_question_mark?(msg)
    msg[-1,1] == '?'
  end

  def message_is_empty?(msg)
    msg.empty?
  end

  def message_is_empty_with_stripped_whitespace?(msg)
    msg.strip.empty?
  end
end
