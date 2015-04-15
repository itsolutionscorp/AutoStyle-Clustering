class Bob
  def hey(message)
    message ||= ''
    case message
    when ''
      'Fine. Be that way.'
    when /\?\z/
      'Sure.'
    when /[a-z]/
      'Whatever.'
    else
      'Woah, chill out!'
    end
  end
end
