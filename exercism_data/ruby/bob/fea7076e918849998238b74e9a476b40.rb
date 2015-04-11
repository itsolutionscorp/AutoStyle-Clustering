class Bob
  def hey(message)
    send('respond_to_'+type_of(message))
  end

  private
  def type_of(message)
    return 'silence' unless message && !message.empty?
    return 'yell' if yelling?(message)
    return 'question' if question?(message)
    'comment'
  end

  def yelling?(str)
    !(str =~ /[a-z]/)
  end

  def question?(str)
    str =~ /\?\z/
  end

  def respond_to_comment
    'Whatever.'
  end

  def respond_to_yell
    'Woah, chill out!'
  end

  def respond_to_question
    'Sure.'
  end

  def respond_to_silence
    'Fine. Be that way.'
  end
end
