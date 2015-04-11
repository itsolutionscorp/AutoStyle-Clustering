class Bob

  SHOUTING = /[A-Z]{2,}/
  ASKING = /\?$/
  QUIET = /^$/

  def hey(message)
    case message.to_s
    when SHOUTING
      'Woah, chill out!'
    when ASKING
      'Sure.'
    when QUIET
      'Fine. Be that way.'
    else
      'Whatever.'
    end
  end

end
