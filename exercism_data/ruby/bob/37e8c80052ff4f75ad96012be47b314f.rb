class Bob
  def hey(message)
    case tone_of message
    when :silence
      'Fine. Be that way.'
    when :question
      'Sure.'
    when :shouting
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  private
  def tone_of(message)
    case
    when message.nil? || message.empty?
      :silence
    when message.end_with?('?')
      :question
    when message.eql?(message.upcase)
      :shouting
    else
      :normal
    end
  end
end
