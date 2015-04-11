class Bob

  def hey(communication)
    if communication.to_s == ''
      'Fine. Be that way.'
    elsif communication.upcase == communication
      'Woah, chill out!'
    elsif communication[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end

end
