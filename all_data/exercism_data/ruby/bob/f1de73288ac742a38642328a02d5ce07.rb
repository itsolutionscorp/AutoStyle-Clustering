class Bob
  def hey(message)
    message ||= ''
    case
    when message.empty?
      'Fine. Be that way.'
    when message[-1] == '?'
      'Sure.'
    when message[/[a-z]/]
      'Whatever.'
    else
      'Woah, chill out!'
    end
  end
end
