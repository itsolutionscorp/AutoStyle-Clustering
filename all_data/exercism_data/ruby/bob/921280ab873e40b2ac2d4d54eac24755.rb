class Bob
  def hey message
    if is_forceful? message
      'Woah, chill out!'
    elsif is_question? message
      'Sure.'
    elsif is_silence? message
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  def is_forceful? message
    message.match(/[A-Z]/) && !message.match(/[a-z]/)
  end

  def is_question? message
    message.end_with? '?'
  end

  def is_silence? message
    message.strip.empty?
  end
end
