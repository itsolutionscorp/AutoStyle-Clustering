class Bob

  def hey message
    return 'Fine. Be that way.' if message.strip == ""
    return 'Woah, chill out!' if message.upcase! == nil
    return 'Sure.' if message.strip.end_with?('?')
    return 'Whatever.' 
  end

end
