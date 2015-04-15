class Bob
  def hey(message)
    case message
    when /[A-Z]{2,}/
      'Woah, chill out!'
    when /\?$/
       'Sure.'
    when ''
      'Fine. Be that way.'
    else
      'Whatever.'
    end
  end
end
